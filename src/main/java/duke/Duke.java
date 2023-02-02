package duke;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/** Duke is an interactive To-Do list created for CS2103T. */
public class Duke extends Application {

    /** Path to saved data */
    private static final String PATH = "/data/duke.txt";

    // duke components
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for Duke. Initializes a Ui, TaskList, and Storage object for use
     * within that session.
     *
     * @param path Path to the saved data
     */
    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList(this.ui);
        this.storage = new Storage(Duke.PATH, this.ui);
    }

    /**
     * Initializes and runs Duke.
     *
     * @param args Ignored
     */
    public static void main(String[] args) {
    }

    /** Runs Duke. */
    public void run() {
        String rawInput;
        Command command;
        boolean isExit = false;

        while (!isExit) {
            try {
                // scan for user input
                rawInput = this.ui.readCommand();
                command = Parser.parse(rawInput);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (DukeException e) {
                this.ui.addToMessage(e.toString());
            } finally {
                this.ui.displayMessage();
                this.storage.saveToFile(this.taskList);
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Scene scene = this.ui.initializeScene();
        stage.setScene(scene);
        stage.show();

        // move stuff from main() here
        // this.storage.readToTaskList(this.taskList);
        // this.ui.showWelcome();
        // this.run();
    }
}
