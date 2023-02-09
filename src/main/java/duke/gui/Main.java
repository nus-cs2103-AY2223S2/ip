package duke.gui;

import java.io.File;
import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class.
 */
public class Main extends Application {

    private Duke dk = new Duke("." + File.separator + "src" + File.separator
            + "main" + File.separator + "data" + File.separator + "duke");

    public Main() {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setDuke(dk);
            controller.greet();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
