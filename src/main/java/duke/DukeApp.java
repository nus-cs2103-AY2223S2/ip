package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Graphical user interface app for Duke.
 */
public class DukeApp extends Application {
    /** Duke for the app */
    private final Duke duke = new Duke();

    /**
     * Starts the app.
     *
     * @param stage the primary stage for this application,
     *      onto which the application scene can be set.
     *      Applications may create other stages, if needed,
     *      but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeApp.class.getResource("/view/MainWindow.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

