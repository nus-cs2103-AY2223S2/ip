package Twofort;

import java.io.IOException;

import Twofort.graphics.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for Twofort
 * @author Bryan Juniano
 */

public class Main extends Application {

    private Twofort twofort = new Twofort();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTwofort(twofort);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}