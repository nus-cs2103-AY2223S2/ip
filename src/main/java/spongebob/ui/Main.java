package spongebob.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import spongebob.Spongebob;

import java.io.IOException;
/**
 * A GUI for Spongebob using FXML.
 */
public class Main extends Application {
    private final Spongebob SPONGEBOB = new Spongebob("data/Spongebob.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Spongebob Chat-box");
            Image icon = new Image(this.getClass().getResourceAsStream("/images/Icon.png"));
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setSpongebob(SPONGEBOB);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
