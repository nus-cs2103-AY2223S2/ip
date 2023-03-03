package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Provides a GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Creates an instance of the FXMLLoader class, which is used to load the FXML file
     * Sets the title of the stage to Duke
     * @param stage the primary stage for this application, onto which the application scene can be set.
     *     Applications may create other stages, if needed, but they will not be
     *     primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/InitialWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Duke");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
