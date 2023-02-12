package babe;

import java.io.IOException;

import babe.controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Babe babe = new Babe();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBabe(babe);
            fxmlLoader.<MainWindow>getController().welcomeUser();
            stage.setTitle("Babe");
            stage.getIcons().add(new Image("/images/kiss.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}