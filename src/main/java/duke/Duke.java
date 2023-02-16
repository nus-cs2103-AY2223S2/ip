package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
     * The main method to start the Duke application.
     *
     * @throws DukeException If there's an error during the execution of the Duke application.

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

    public String getResponse(String input) {
        return this.start(input);
    }



}
