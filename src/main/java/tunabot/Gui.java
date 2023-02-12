package tunabot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class that handles the GUI for user interaction.
 */
public class Gui extends Application {
    private TunaBot tunaBot;

    /**
     * Constructor that initializes a GUI.
     */
    public Gui() {
        this.tunaBot = new TunaBot();
    }

    /**
     * Overrides the {@code start} method for JavaFx.
     *
     * @param stage the primary stage for this application, onto which the application scene can be
     *         set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("TunaBot");
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDuke(tunaBot);
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
