package duke.component;

import java.io.IOException;

import duke.TaskList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final TaskList tasks = new TaskList();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("ChikaDuke");
            fxmlLoader.<MainWindow>getController().setDuke(tasks);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
