package gui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke("data/tasks.txt");

    /**
     * Exits the application after three second.
     */
    public static void exit() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 3000);
    }

    /**
     * Executes when the application starts.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            BorderPane bp = fxmlLoader.load();
            Scene scene = new Scene(bp);
            Image icon = new Image("/images/icon.png");
            stage.getIcons().add(icon);
            stage.setTitle("Duke");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this.duke);
            fxmlLoader.<MainWindow>getController().run();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
