package chad.frontend;

import java.io.IOException;

import chad.backend.Chad;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static Chad chad;

    public static void exit() {
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Chad - Your Personal Digital Assistant");
            stage.setScene(scene);
            chad = new Chad();
            fxmlLoader.<MainWindow>getController().setChad(chad);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        chad.getResponse("bye");
        super.stop();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
