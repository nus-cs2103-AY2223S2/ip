package duke;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import task.TaskList;

/**
 * Duke class is the main class and the beginning point of execution for the Duke chatbot.
 *
 * @author      Tseng Chen-Yu
 * @version     %I%, %G%
 * @since       1.0
 */
public class Duke extends Application{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    @Override
    public void start(Stage stage) {
        storage = new Storage();
        tasks = new TaskList(storage.load());
        ui = new Ui(stage);
        setupProgramIcon(stage);
    }

    private void setupProgramIcon(Stage stage) {
        stage.getIcons().add(new Image("/images/duke.png"));
    }
}
