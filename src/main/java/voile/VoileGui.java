package voile;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import voile.common.Storage;
import voile.controller.MainWindow;
import voile.model.Model;

/**
 * Driver for the GUI version of the application.
 */
public class VoileGui extends Application {

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
        stage.setTitle("Voile");
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
