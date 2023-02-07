import java.io.IOException;

import duke.DukeException;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
            fxmlLoader.<MainWindow>getController().setDuke(new JavaFxUi());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException {
        try {
            Command command = parser.parseCommand(input);
            command.assign(taskList, ui);
            String output = command.execute();
            return "NoDuKo Says: " +  output;
        } catch (DukeException e) {
            return "NoDuKo Says: " + e.getMessage();
        }
    }

    public static void main() throws DukeException {
        Application.launch(JavaFxUi.class);

    }
}
