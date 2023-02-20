package duke;

import java.util.Scanner;

import duke.commands.Command;
import duke.commands.Exit;
import duke.commands.Parser;
import duke.dukeexception.DukeException;

/**
 * This is the core class of the program.
 *
 * @author Shi Jia Ao
 */
public class Duke {
    private static final String FILEPATH = "data";
    private static final String TERMINATION_STATEMENT = "Done";
    private final Ui ui;
    private final Storage storage;
    private TaskList toDoList;
    private boolean isTerminated;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(Duke.FILEPATH);
        this.toDoList = this.storage.initialize();
        this.isTerminated = false;
    }

    /**
     * Generates a response based on the user input.
     *
     * @param input The user input.
     * @return A String representing the program's response.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input);
        try {
            Command currCommand = parser.process();
            executeUserInput(currCommand);
            String response = ui.getCommandMessage(currCommand);
            this.isTerminated = response.startsWith(Duke.TERMINATION_STATEMENT);
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

    /**
     * Runs the program using command line UI.
     */
    private void runWithCommandLineUi() {
        this.ui.printWelcome();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            Parser parser = new Parser(command);
            Command currCommand;
            try {
                currCommand = parser.process();
                currCommand.execute(this.toDoList);
                this.ui.printCommandMessage(currCommand);
            } catch (DukeException ex) {
                this.ui.printExceptionMessage(ex);
                continue;
            }
            if (currCommand instanceof Exit) {
                this.ui.printGoodbye();
                this.storage.update(this.toDoList);
                break;
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runWithCommandLineUi();
    }
}
