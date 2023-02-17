package duke;

import java.io.IOException;

import duke.exception.DukeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {
    /** Simple greeting message */
    private static final String GREETING = "BOOTING UP... How may I help you?";

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
            stage.setTitle("DukePro for Pros");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/DaDuke.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            duke.loadTasks();
            mainWindow.showDukeMessage(GREETING);
        } catch (DukeException e) {
            mainWindow.showDukeMessage(GREETING + "\nLOADING... Failed to load tasks. Empty task list has been created.");
        }
    }

}
