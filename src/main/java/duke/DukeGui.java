package duke;

import duke.common.Storage;
import duke.controller.MainWindow;
import duke.model.Model;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Driver for the GUI version of the application.
 */
public class DukeGui extends Application {

    private Model model;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        model = new Model(Storage.readTaskList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(model);
        Scene scene = new Scene(mainWindow);
        Image icon = new Image(getClass().getResourceAsStream("/images/Icon.jpg"));
        stage.setTitle("Duke");
        stage.getIcons().add(icon);
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
