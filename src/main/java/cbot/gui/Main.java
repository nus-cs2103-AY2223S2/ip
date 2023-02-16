package cbot.gui;

import java.io.IOException;

import cbot.Cbot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Cbot (!!) using FXML.
 */
public class Main extends Application {

    private final Cbot cbot = new Cbot();

    /**
     * Starts the Cbot application.
     *
     * @param stage the primary stage for this application, onto which
     *         the application scene can be set. Applications may create other stages, if needed,
     *         but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Cbot v0.5");
            fxmlLoader.<MainWindow>getController().setCbot(cbot);
            fxmlLoader.<MainWindow>getController().greetUser();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the application should stop.
     * There is a forced delay to allow the user to read the exit message before the window closes.
     *
     * @throws InterruptedException If the delay is interrupted.
     */
    @Override
    public void stop() throws InterruptedException {
        Thread.sleep(750);
    }
}
