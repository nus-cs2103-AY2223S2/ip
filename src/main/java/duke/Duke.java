package duke;

import exception.DukeException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import parser.Parser;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;


/**
 * Represents the whole program, containing the main program for the whole bot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    /**
     * Constructor for a Duke Object.
     * Initializes the whole program as well as required objects.
     * If path leads to a file that does not exist, the file will be created.
     *
     * @param filePath String of path to stored list from previous sessions.
     * @return Duke object.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }


    public String getResponse(String input) {
        // Supposed to change
        String response = Parser.parseCommands(input, this.tasks, this.ui, this.storage);
        if (!response.isEmpty()) {
            return response;
        }
        Task item;
        try {
            item = Parser.parseEcho(input);
        } catch (DukeException e) {
            // TODO: handle exception
            return e.getMessage();
        }

        tasks.addTask(item);
        response += ui.showAddedMessage(item);
        response += ui.printListNumber(tasks.getList());
        return response;
    }


}
