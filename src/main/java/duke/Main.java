package duke;

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

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(Main.class.getResource("/style/stylesheet.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle("Chopper Helpdesk");
            fxmlLoader.<MainWindow>getController().setDuke(this.duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
