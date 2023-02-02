package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Represents a GUI for the chatbot using FXML. */
public class Main extends Application {
    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorpane = fxmlLoader.load();
            Scene scene = new Scene(anchorpane);
            stage.setScene(scene);
            MainWindow mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setDuke(duke);
            mainWindow.setStage(stage);
            stage.show();

            stage.setTitle("Boo");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(800.0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
