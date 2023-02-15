package membot.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import membot.Membot;
import membot.ui.controller.MainWindow;
import membot.view.UiPrinter;

/**
 * The main driver class for initialising JavaFX GUI for Membot.
 */
public class Gui extends Application {
    private static final String MAIN_WINDOW_FXML = "/view/MainWindow.fxml";
    private static final String MEMBOT_ICON = "/assets/membotIcon.png";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MAIN_WINDOW_FXML));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream(MEMBOT_ICON)));
            stage.setTitle("Membot");
            stage.setMinWidth(400);
            stage.setMinHeight(600);
            stage.setResizable(false);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMembot(
                    new Membot(new UiPrinter(fxmlLoader.<MainWindow>getController().getPrinter()), false));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
