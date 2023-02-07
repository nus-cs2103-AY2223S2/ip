package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {
    /** Simple greeting message */
    private static final String GREETING = "Welcome to Duke. How may I help you?";

    private Duke duke = new Duke();
    private MainWindow mainWindow;

    public static void launch(String[] args) {
        Application.launch(Gui.class, args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setDuke(duke);
            mainWindow.showDukeMessage(GREETING);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
