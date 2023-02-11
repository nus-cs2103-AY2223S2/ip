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
    private static final String FILEPATH = "data";
    private final Ui ui;
    private final Storage storage;
    private TaskList toDoList;
    private boolean isTerminated;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(Duke.FILEPATH);
        this.toDoList = this.storage.initialise();
        this.isTerminated = false;
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
            String response = ui.getCommandMessage(currCommand);
            this.isTerminated = response.startsWith("Done") ? true : false;
            return response;
        } catch (DukeException ex) {
            return ui.getExceptionMessage(ex);
        }
    }

    public boolean hasTerminated() {
        return this.isTerminated;
    }

    private void executeUserInput(Command command) {
        command.execute(this.toDoList);
        this.storage.update(this.toDoList);
    }

    // todo: remove main or change it to another method as it is no longer needed
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printWelcome();
        // todo: change this to constant
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
