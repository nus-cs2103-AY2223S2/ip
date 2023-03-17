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

/**
 * Manages different routes in the application.
 */
public class RouterManager {
    private static RouterManager router;
    private static final Logger logger = Logger.getLogger(RouterManager.class.getName());
    private final Duke duke = new Duke("duke.txt");
    private final Stage stage;

    private RouterManager(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates a router manager instance if there is none instantiated, else create one.
     *
     * @param stage The stage needed to set the scene.
     * @return The router singleton.
     */
    public static RouterManager createRouterSingleton(Stage stage) {
        if (router == null) {
            router = new RouterManager(stage);
        }
        return router;
    }

    /**
     * Sets the scene to the main application view.
     */
    public void showMainView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the scene to the landing view.
     */
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
