package roody.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import roody.Roody;

/**
 * A GUI for Duke using FXML.
 */
public class RoodyMain extends Application {

    private Roody roody = new Roody("./data/Roody.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RoodyMain.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRoody(roody);
            stage.show();
            fxmlLoader.<MainWindow>getController().sendMessage(roody.getResponse("start"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
