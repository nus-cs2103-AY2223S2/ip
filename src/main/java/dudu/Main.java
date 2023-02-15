package dudu;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Dudu dudu = new Dudu("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Dudu");
            stage.setMinHeight(500);
            stage.setMinWidth(450);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDudu(dudu);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
