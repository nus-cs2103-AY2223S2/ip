package chad;

import java.io.IOException;

import chad.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Chad chad = new Chad("./data/duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Chad");
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setChad(chad);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
