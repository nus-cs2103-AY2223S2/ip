package gui.managers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import duke.Duke;
import gui.Main;
import gui.components.Landing;
import gui.components.MainWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RouterManager {
    private final Duke duke = new Duke("duke.txt");
    private final Stage stage;

    public RouterManager(Stage stage) {
        this.stage = stage;
    }

    public void showMainView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
        } catch (IOException ex) {
            Logger.getLogger(RouterManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showLandingView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Landing.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Landing>getController().initRouter(this);
        } catch (IOException ex) {
            Logger.getLogger(RouterManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
