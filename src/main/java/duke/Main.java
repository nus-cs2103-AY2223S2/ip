package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setRoot(new AnchorPane());
            fxmlLoader.setLocation(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(Main.class.getResource("/styles/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(true);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}