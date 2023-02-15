package taskgenie;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.MainWindow;
/**
 * This program allows you to start the TaskGenie application.
 */
public class Main extends Application {
    private TaskGenie taskGenie = new TaskGenie("data/taskGenie.txt");
    public static void main(String[] args) throws IOException {
        TaskGenie.main(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("TaskGenie");
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setTaskGenie(taskGenie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
