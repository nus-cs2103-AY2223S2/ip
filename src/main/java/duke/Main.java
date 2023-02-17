package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final Duke duke = new Duke("./userRecords/records.txt");

    /**
     * Starts the stage.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            scene.getRoot().setStyle("-fx-font-family: 'ui-serif'");

            fxmlLoader.<MainWindow>getController().welcome();
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

