//@@author 9fc70c892-reused
//Source: https://se-education.org/guides/tutorials/javaFx.html
//@@author
import java.io.IOException;

import core.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ux.MainWindow;

/**
 * A GUI for core.Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * This method is called for javafx to run.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set. Applications
     *              may create other stages, if needed, but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            stage.setTitle("Waffles!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called for javafx is closed.
     *
     * This will perform all the necessary shutdown features.
     */
    @Override
    public void stop() {
        duke.goodbye();
    }
}
