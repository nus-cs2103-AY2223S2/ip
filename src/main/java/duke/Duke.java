package duke;

import command.Command;
import exception.DukeException;
import task.Tasklist;

/**
 * The main class for the Duke task management application.
 *
 * */
public class Duke {
    private Ui userInterface;
    private Storage backend;
    private Tasklist tasklist;



    /**
     * Constructor for the Duke class, which takes in a file path as a parameter.
     *
     * @param filePath The file path to the backend storage file.
     */

    public Duke(String filePath) {
        this.userInterface = new Ui();
        this.backend = new Storage(filePath);
        try {
            this.tasklist = new Tasklist(backend.load(), backend);
        } catch (DukeException e) {
            userInterface.displayErrorMessage(e.getMessage());
            this.tasklist = new Tasklist();
        }
        this.userInterface = new Ui();

    }

    /**
     * Starts processing the user's input by parsing it into a command and executing it.
     * @param input the user's input to process
     * @return the result of executing the command generated from the user's input
     */
    public String start(String input) {
        try {
            Parser parser = new Parser(input);
            Command command = parser.parseCommand();
            return command.execute(userInterface, tasklist, backend);
        } catch (DukeException e) {
            return userInterface.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Processes the user's input and returns the Duke's response.
     * @param input the user's input to process
     * @return the Duke's response to the user's input
     */
    public String getResponse(String input) {
        return this.start(input);
    }

}
