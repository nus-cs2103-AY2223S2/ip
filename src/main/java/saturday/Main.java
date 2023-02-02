package saturday;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import saturday.utilities.Storage;

/**
 * A GUI for Saturday using FXML.
 */
public class Main extends Application {

    private Saturday saturday = new Saturday(Storage.getFilePath());

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Saturday");
//            stage.setFullScreen(true);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setSaturday(saturday);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}