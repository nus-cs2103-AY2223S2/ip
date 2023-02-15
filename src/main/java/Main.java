import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents a GUI for Jeo using FXML.
 * @author Goh Jun How
 * @version 0.3
 */
public class Main extends Application {
    private static final String PATH = "./data.txt";
    private final JeoBot jeo = new JeoBot(PATH);

    /**
     * Starts the GUI.
     * @param stage Stage that JavaFX provides.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            Image icon = new Image(Objects.requireNonNull(Main.class.getResource(
                    "/images/DaJeo.png")).toString());
            stage.getIcons().add(icon);
            stage.setTitle("JeoBot");
            fxmlLoader.<MainWindow>getController().setJeo(jeo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
