package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class serving as a bridge between MainWindow and Duke
 */
public class Main extends Application {

    private static final String DEFAULT_SAVE_PATH = "./data/data.txt";

    private final Duke duke;

    /**
     * Constructor for Main, given a save data location.
     * @param savePath File path for saved data.
     */
    public Main(String savePath) {
        duke = new Duke(savePath);
    }

    /**
     * Constructor for Main, given no save data location.
     * Uses default save path.
     */
    public Main() {
        this(DEFAULT_SAVE_PATH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Arii");
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
