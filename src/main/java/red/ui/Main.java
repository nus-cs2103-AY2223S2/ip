package red.ui;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import red.Red;

/**
 * A GUI for Red using FXML.
 */
public class Main extends Application {

    private Red duke = new Red();

    /**
     * Loads the application for interaction with the user
     *
     * @param stage the image we show the user
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRed(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
