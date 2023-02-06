package kira;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kira.ui.gui.MainWindow;

/**
 * Main app to run KiraBot GUI.
 */
public class KiraApp extends Application {

    private KiraBot kiraBot = new KiraBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(KiraApp.class.getResource("/view/MainWindow.fxml"));
            GridPane ap = (GridPane) fxmlLoader.load();
            Scene scene = new Scene(ap, 400, 600);
            stage.setScene(scene);
            stage.setTitle("KiraBot~");
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setBot(kiraBot);
            controller.setup();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
