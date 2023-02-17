package duke;

import duke.common.Storage;
import duke.controller.MainWindow;
import duke.model.Model;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Driver for the GUI version of the application.
 */
public class DukeGui extends Application {

    private Model model;
    private MainWindow window;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        model = new Model(Storage.readTaskList());
        window = new MainWindow(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(window);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        Storage.writeTaskList(model.getTaskList());
    }
}
