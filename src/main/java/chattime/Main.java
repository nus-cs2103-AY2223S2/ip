package chattime;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// @@author Jeffry Lum
// Adapted from Guides for SE Student Project- Java FX Tutorial part 4

/**
 * Class for GUI of bot.
 */
public class Main extends Application {
    private Chattime chattime = new Chattime("");
    private Image iconImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/image/chattime.png")));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            ap.getStyleClass().add("anchor-pane");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBot(chattime);
            stage.setResizable(true);
            stage.getIcons().add(iconImage);
            stage.setTitle("CHATTIME");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
