package duke;

import java.io.File;
import java.io.IOException;

import duke.fxui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Duke duke = new Duke("data" + File.separator + "tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox vBox = fxmlLoader.load();
            Scene scene = new Scene(vBox);
            stage.setScene(scene);
            stage.setTitle("Duke Chatbot");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
