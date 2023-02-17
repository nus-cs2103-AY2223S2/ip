import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for UwUTaskMaster using FXML.
 */
public class Main extends Application {
    /**
     * Initializes and displays the main application window.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            UwUTaskmaster taskmaster = new UwUTaskmaster(stage);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTaskMaster(taskmaster);
            stage.setTitle("UwU TaskMaster");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
