package duke.client;

import java.io.IOException;
import java.net.URL;

import duke.DukeException;
import duke.client.components.MainWindow;
import duke.Duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String RESOURCE_PATH = "/view/MainWindow.fxml";
    @Override
    public void start(Stage stage) {
        try {
            Duke duke = new Duke();
            URL mainWindowResource = Main.class.getResource(RESOURCE_PATH);
            FXMLLoader fxmlLoader = new FXMLLoader(mainWindowResource);
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().initialize();
            stage.show();
        } catch (IOException | DukeException exception) {
            exception.printStackTrace();
        }
    }
}
