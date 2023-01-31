/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents the main program of Duke.
 */
public class Duke {

    private static boolean isStart = false;

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Represents the set of commands by the user.
     */
    public enum Commands {
        bye,
        mark,
        unmark,
        list,
        todo,
        deadline,
        event,
        delete,
        find
    }

    /**
     * Constructor for the Duke class.
     *
     * @param filepath The file path for the data stored in the txt file.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * The start of the program.
     */
    public String run(String input) {
        // Description of the task.
        String description = "";
        // Return of the final String to add.
        String dukeText = "";

        if(!isStart) {
            dukeText += ui.welcome();
        }
        isStart = true;
        description = "";
        Parser userParse = new Parser(input);
        try {
            Commands userCommand = userParse.checkCommand(userParse.command);
            switch(userCommand) {
            case bye:
                dukeText += ui.bye();
                break;
            case mark:
                dukeText += ui.markDisplay(tasks.mark(userParse), userParse);
                break;
            case unmark:
                dukeText += ui.unmarkDisplay(tasks.unmark(userParse), userParse);
                break;
            case list:
                dukeText += "Here are the list of tasks:\n";
                dukeText += ui.list(tasks);
                break;
            case todo:
                tasks.addTodo(description, userParse);
                dukeText += ui.addTodoMessage(tasks);
                break;
            case deadline:
                tasks.addDeadline(description, userParse);
                dukeText += ui.addDeadlineMessage(tasks);
                break;
            case event:
                tasks.addEvent(description, userParse);
                dukeText += ui.addEventMessage(tasks);
                break;
            case delete:
                dukeText += ui.deleteMessage(tasks, tasks.delete(userParse));
                break;
            case find:
                dukeText += ui.findMessage(tasks.find(userParse));
                break;
            default:
                break;
            }
            storage.write(tasks);
        } catch (DukeException e) {
                dukeText += e.getMessage();
        }
        return dukeText;
    }


    /**
     * Returns the duke response.
     *
     * @param input The input from the user.
     * @return The response of Duke.
     */
    protected String getResponse(String input) {
        Duke currDuke = new Duke("data/duke.txt");
        return "Duke: \n" + currDuke.run(input);
    }
}
