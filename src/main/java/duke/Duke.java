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
    }

    /**
     * The main method to start the Duke application.
     *
     * @throws DukeException If there's an error during the execution of the Duke application.
     */
    public void start() throws DukeException {
        userInterface.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = userInterface.readCommand();
                userInterface.showLine(); // show the divider line ("_______")
                Parser parser = new Parser(fullCommand);
                Command command = parser.parseCommand();
                command.execute(userInterface, tasklist, backend);
                isExit = command.isExit();

            } catch (DukeException e) {
                userInterface.displayErrorMessage(e.getMessage());
            } finally {
                userInterface.showLine();
            }
        }
    }

    /**
     * The main method for the Duke application.
     *
     * @param args The command line arguments passed to the Duke application.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        try {
            duke.start();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
