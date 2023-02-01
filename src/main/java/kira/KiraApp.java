package kira;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main app to run KiraBot GUI.
 */
public class KiraApp extends Application {

    private KiraBot kiraBot = new KiraBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(KiraApp.class.getResource("/view/KiraController.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            KiraController controller = fxmlLoader.<KiraController>getController();
            controller.setBot(kiraBot);
            controller.setup();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
