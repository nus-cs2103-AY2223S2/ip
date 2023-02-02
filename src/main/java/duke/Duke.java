package duke;

import duke.ui.DialogBox;
import duke.utils.ReplyString;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.exceptions.DukeException;
import duke.exceptions.MemoryFailedException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.ui.Ui;

/**
 * Represents the entry point for the Duke application. The main function resides in this class.
 */
public class Duke {

    private Storage storage;
    private TaskList allTasks;
    private static String[] defaultMemoryPathArray = {".", "memory.txt"};

    public Duke() {
        this(defaultMemoryPathArray);
    }

    public Duke(String[] memoryPathArray) {
        this.storage = new Storage(memoryPathArray);
        this.allTasks = new TaskList();
        try {
            this.storage.loadTasks(this.allTasks);
        } catch (MemoryFailedException e) {
            // TODO: Handle this error in a user-friendly manner
            e.printStackTrace();
        }
    }

    /**
     * Takes in a command and outputs the appropriate response.
     */
    public String getResponse(String command) {
        String response = "There was an error with your query!";
        try {
            response = Parser.handleCommands(command, this.allTasks);
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
        return response;
    }

    public void saveTasks() {
        this.storage.saveTasks(this.allTasks);
    }

}
