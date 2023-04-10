package rick;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rick.gui.MainWindow;

/**
 * Represents a class that generates a GUI for Rick using FXML.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class MainApp extends Application {

    private final Rick rick = new Rick();

    /**
     * Initializes this App's GUI.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene can be set. Applications may
     *              create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinWidth(400);
            stage.setMinHeight(300);
            stage.setTitle("Rick and Duke");
            fxmlLoader.<MainWindow>getController().setRick(rick);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
