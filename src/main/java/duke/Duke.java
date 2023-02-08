package duke;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Parser;
import duke.util.Storage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 * Duke class: serves as the entry class to the application
 */
public class Duke {
    private TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    /**
     * Duke class constructor
     */
    public Duke() {
        ui = new Ui();
        String filePath = "./data/duke.txt";
        storage = new Storage(filePath, ui);
        try {
            taskList = storage.load(filePath);
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        }
    }


    /**
     * Generate a response to user input.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input, taskList, ui, storage);
            return cmd.execute();

        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

}
