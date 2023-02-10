package duke;

import java.io.IOException;

import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DukeGui extends Application {
    private static final String MAIN_WINDOW_RESOURCE_PATH = "/view/MainWindow.fxml";

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Bot bot = new Bot();

    @Override
    public void start(Stage stage) {
        try {
            bot.init();
        } catch (DukeException e) {
            // TODO: Should exit with error.
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeGui.class.getResource(MAIN_WINDOW_RESOURCE_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBot(bot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
