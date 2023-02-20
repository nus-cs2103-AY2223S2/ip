package kal.gui;

import java.io.IOException;

import kal.Kal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Kal using FXML.
 */
public class Main extends Application {
    private static final String MAIN_WINDOW_FILEPATH = "/view/MainWindow.fxml";
    private Kal kal = new Kal();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Main.MAIN_WINDOW_FILEPATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKal(kal);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
