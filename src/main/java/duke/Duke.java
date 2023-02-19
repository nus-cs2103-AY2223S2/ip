package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UserInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
        assert stage != null : "Stage cannot be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            ui = fxmlLoader.getController();
            fxmlLoader.<UserInterface>getController().setDuke(this);
            stage.setScene(scene);
            stage.show();

            ui.showGreeting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(String input) {
        try {
            parser.parse(input).execute(list, ui, storage);
        } catch (Exception e) {
            ui.showMessage(e.getMessage());
        }
    }

}
