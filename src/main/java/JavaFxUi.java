import java.io.IOException;

import duke.DukeException;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.*;
import javafx.stage.Stage;

/**
 * Main JavaFxUi class to create interface.
 */
public class JavaFxUi extends Application {
    private static Parser parser = new Parser();
    private static Ui ui = new Ui();
    private static Storage storage;
    private static TaskList taskList;

    static {
        try {
            storage = new Storage();
            taskList = storage.readFromFile();
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setUi(new JavaFxUi());
            stage.show();
            assert ap != null : "AnchorPane cannot be null";
            assert scene != null : "Scene cannot be null";
            assert fxmlLoader != null : "FXMLLoader cannot be null";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns response from Duke.
     * @param input user input
     * @return response from Duke
     */
    String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            command.assign(taskList, ui);
            String output = command.execute();
            return "NoDuKo Says: " + output;
        } catch (DukeException e) {
            return "NoDuKo Says: " + e.getMessage();
        }
    }

    /**
     * Main method to run Duke.
     */
    public static void main() {
        Application.launch(JavaFxUi.class);

    }
}
