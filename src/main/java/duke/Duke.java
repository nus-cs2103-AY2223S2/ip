package duke;
import java.util.Scanner;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;
import duke.ui.DialogBox;
import duke.parser.Parser;
import duke.exceptions.DukeException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A chat bot program.
 */
public class Duke {
    /** Parses user input. */
    private Parser parser;

    /** The path to the file that stores the Tasks on the hard drive. */
    private String FILE_PATH = "duke.txt";

    /**
     * Constructs a new Duke session.
     */
    public Duke() {
           Ui ui = new Ui();
           Storage storage = new Storage(FILE_PATH);
            //Attempts to load Tasks from hard drive into the list of Tasks.
            TaskList tasks = new TaskList(storage.load());
            this.parser = new Parser(ui, storage, tasks);
    }

    /**
     * Gets an appropriate response to the user input.
     * @param input the user's input
     * @return the response to the user
     */
    public String getResponse(String input) {
        try {
             String parsedOutput =  parser.parse(input);
             return parsedOutput;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}


