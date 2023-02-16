package iris;

import java.io.IOException;

import iris.ui.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Iris using FXML.
 */
public class Main extends Application {

    private final Iris iris = new Iris();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Iris");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setIris(iris);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * closes the program
     */
    public static void quit() {
        Platform.exit();
        System.exit(0);
    }
}
