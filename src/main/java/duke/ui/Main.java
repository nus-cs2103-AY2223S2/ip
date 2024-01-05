package duke.ui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            // Set the Duke object in the controller
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            // Set the window icon
            stage.getIcons().add(new Image("/images/icon.png"));
            stage.setTitle("Mr. Bear");
            stage.isResizable();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
