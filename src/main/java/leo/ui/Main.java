package leo.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import leo.Leo;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Leo leo = new Leo("/leo.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.setFill(Color.ORANGE);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setLeo(leo);

            stage.getIcons().add(new Image("/images/lion.png"));
            stage.setTitle("Leo");
            stage.isResizable();
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
