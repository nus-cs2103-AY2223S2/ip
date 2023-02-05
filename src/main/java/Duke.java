import duke.gui.Duke_GUI;
import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Duke.
 */
public class Duke extends Application {


    private final Duke_GUI UI = new Duke_GUI();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Application.launch(Duke_GUI.class, args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(UI);
            stage.show();
            stage.setOnCloseRequest(event -> {
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

