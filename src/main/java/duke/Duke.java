package duke;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.application.Platform;
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
        this.ui = new Ui(this);
        this.taskList = new TaskList(this.ui);
        this.storage = new Storage(Duke.PATH, this.ui);
    }

    /** Runs Duke. */
    public void run(String rawInput) {
        Command command;
        boolean isExit = false;

        try {
            command = Parser.parse(rawInput);
            command.execute(taskList, ui);
            isExit = command.isExit();
        } catch (DukeException e) {
            this.ui.addToMessage(e.toString());
        } finally {
            this.ui.displayMessage();
            this.storage.saveToFile(this.taskList);
            if (isExit) {
                Platform.exit();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        this.ui.initializeStage(stage);
        this.storage.readToTaskList(this.taskList);
        this.ui.showWelcome();
    }
}
