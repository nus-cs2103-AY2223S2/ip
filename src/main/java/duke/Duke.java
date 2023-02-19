package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exceptions.IOException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UserInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the Duke task manager.
 *
 * @author Samarth Verma
 */
public class Duke extends Application {

    private UserInterface ui;
    private TaskList list;
    private Parser parser;
    private Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /** Creates a new Duke object. */
    public Duke() {
        ui = new UserInterface();
        parser = new Parser();

        list = new TaskList();
        Path filePath = Paths.get(".", "data", "duke.txt");
        storage = new Storage(filePath.toString());

        try {
            list = storage.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<UserInterface>getController().setDuke(this);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
