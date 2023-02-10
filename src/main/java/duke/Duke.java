package duke;

import java.util.Scanner;

import duke.commands.Command;
import duke.commands.Exit;
import duke.commands.Parser;
import duke.dukeexception.DukeException;

/**
 * This is the main class of the program.
 *
 * @author Shi Jia Ao
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList toDoList;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data");
        this.toDoList = this.storage.initialise();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        Parser parser = new Parser(input);
        try {
            Command currCommand = parser.process();
            executeUserInput(currCommand);
            return ui.getCommandMessage(currCommand);
        } catch (DukeException ex) {
            return ui.getExceptionMessage(ex);
        }
    }

    private void executeUserInput(Command command) {
        command.execute(toDoList);
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printWelcome();
        Storage storage = new Storage("data");
        TaskList toDoList;
        toDoList = storage.initialise();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            Parser parser = new Parser(command);
            Command currCommand;
            try {
                currCommand = parser.process();
                currCommand.execute(toDoList);
                ui.printCommandMessage(currCommand);
            } catch (DukeException ex) {
                ui.printExceptionMessage(ex);
                continue;
            }
            if (currCommand instanceof Exit) {
                ui.printGoodbye();
                storage.update(toDoList);
                break;
            }
        }
        sc.close();
    }
}
