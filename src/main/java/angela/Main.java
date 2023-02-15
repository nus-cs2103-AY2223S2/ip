package angela;

import java.io.IOException;

import angela.exception.AngelaException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Angela using FXML.
 */
public class Main extends Application {

    private Angela angela;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Angela");

            angela = new Angela("data/angela.txt");
            fxmlLoader.<MainWindow>getController().setAngela(angela);
            stage.show();
        } catch (IOException | AngelaException e) {
            e.printStackTrace();
        }
    }
}
