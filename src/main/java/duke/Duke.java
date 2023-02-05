package duke;
import duke.command.Command;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main program for iP
 */
public class Duke extends Application {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Default constructor
     * @throws Exception
     */
    public Duke() throws Exception {
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
        try {
            taskList = new TaskList(this.storage.loadTasks());
        } catch (DukeException duke) {
            TypeOfTask errorTask = duke.getErrorTask();
            if (errorTask == TypeOfTask.storage) {
                System.out.println(duke.getMessage());
                throw new Exception("goodbye!");
            } else {
                System.out.println(duke.getMessage());
            }

        }
    }

    /**
     * Runs the program in a loop while waiting for the user's inputs.
     * @throws Exception when user inputs are not legal
     */
    public void run() throws Exception {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = this.parser.parse(fullCommand);
                command.execute(this.taskList, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException duke) {
                System.out.println(duke.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * Main method for Duke class
     * @param args
     * @throws Exception when unforseen error occurs.
     */
    public static void main(String[] args) throws Exception {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        // Labels are nodes in JavaFX
        Label helloWorld = new Label("HelloWorld!"); // Creating a new label control
        Scene scene = new Scene(helloWorld); // Setting the scene to our label

        stage.setScene(scene); // setting the stage to show our screen
        stage.show(); // Render the stage
    }

}
