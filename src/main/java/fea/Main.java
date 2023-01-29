package fea;

import java.io.File;
import java.io.IOException;

import fea.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for FEA using FXML.
 */
public class Main extends Application {
    private String filePath = String.format("data%sfea.txt", File.separator);
    private Fea fea = new Fea(filePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Fate Eggplant Assistant");
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/logo.png")));
            stage.setScene(scene);
            stage.sizeToScene();
            fxmlLoader.<MainWindow>getController().setFea(fea);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.show();
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
