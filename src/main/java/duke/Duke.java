package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * <h1>Duke Task Manager</h1>
 * The Duke program a that the creation of tasks such as Todo, Event and
 * Deadline. The program allows to listing of those added tasks and mark them as
 * done and not done, it also allows deletion of wrongly added task.
 *
 * @author Leng Wei Cong, Justin
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
    * Constructor.
    * @param filePath the path to the text file that stores the tasks
    */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load());
    }

    /**
     * Another constructor for JavaFX
     */
    public Duke() {
        // Empty
    }

    /**
    * Runs Duke.
    */
    public void run() {
        this.ui.showWelcome();
        this.handleRequest();
        this.ui.showExit();
    }

    /**
    * Handles the request from user input.
    */
    public void handleRequest() {
        while (true) {
            String fullcommand = ui.readCommand();
            Command command = Parser.parse(fullcommand);

            // Breaks when is an exit command
            if (command.isExit()) {
                break;
            }

            // Executes command
            command.execute(tasks, ui, storage);

            ui.showDivider();
        }
    }

    /**
    * Prints whatever the user inputs.
    * @param input the user input
    */
    public void echo(String input) {
        ui.showMessage(input);
    }

    /**
     * Starts JavaFX
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
    * This is the main method which makes use of run method.
    * @param args Unused.
    */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
