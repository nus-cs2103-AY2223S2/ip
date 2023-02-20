package duke;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;
import task.Task;
import ui.UI;

/**
 * Duke.Duke is a program that help user track list of tasks, it can take in todos, deadline and events tasks and allow
 * users to mark tasks as done or undone and delete the task.
 * @author CShuwen
 * @version 1.0
 * @since 0.0
 */
public class Duke {
    private Storage storage;
    private UI ui;
    private Response response;

    /**
     * Initialises Duke program
     */
    public Duke() {
        this.storage = new Storage("duke.txt");
        this.ui = new UI();
        this.response = new Response();
    }

    /**
     * sets initial GUI
     */
    public void setGui(Stage stage) {
        stage.setTitle("Crayon Shin Chan ChatBot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.getIcons().add(new Image("/images/icon.jpeg"));
    }

    /**
     * returns the response from user input
     *
     * @param input user input in String form
     * @return resposne in String form
     */
    public String getResponse(String input) {
        int len = input.length();
        int i;
        Parser parser = new Parser();
        String command = parser.parse(input);
        ArrayList<Task> taskList = storage.load();
        String res = "";
        switch (command) {
        case "BYE":
            return response.getByeResponse();
        case "LIST":
            return response.getListResponse(taskList);
        case "DELETE":
            return response.getDeleteResponse(input, taskList, storage);
        case "MARK":
            return response.getMarkResponse(input, taskList, storage);
        case "UNMARK":
            return response.getUnmarkResponse(input, taskList, storage);
        case "TODO":
            return response.getToDoResponse(input, taskList, storage);
        case "DEADLINE":
            return response.getDeadlineResponse(input, taskList, storage);
        case "EVENT":
            return response.getEventResponse(input, taskList, storage);
        case "FIND":
            return response.getFindResponse(input, taskList);
        case "ERROR":
            return response.getErrorResponse();
        case "UNDO":
            return response.getUndoResponse(storage);
        default:
            break;
        }
        return "I don't know what that means";
    }
}