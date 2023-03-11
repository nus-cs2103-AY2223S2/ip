package roody;

import java.util.ArrayList;

import javafx.application.Application;
import roody.commands.Command;
import roody.exceptions.RoodyException;
import roody.gui.RoodyMain;
import roody.tasks.Task;

/**
 * Represents a CLI chatbot named Roody.
 */
public class Roody {
    /** Stores tasks */
    private ArrayList<Task> taskList;

    /** Manages loading/saving of task data */
    private Storage storage;

    /** Manages GUI */
    private Ui ui;

    /**
     * Creates a chatbot with specified filepath to task data.
     * @param filepath The filepath to task data.
     */
    public Roody(String filepath) {
        // Assumed no more than 100 tasks
        this.taskList = new ArrayList<Task>();
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        taskList = storage.loadFile();
    }

    /**
     * Returns a String response to given input.
     * @param input A String to respond to.
     * @return A Roody response.
     */
    public String getResponse(String input) {
        String message = "";
        try {
            Command c = Parser.parse(input);
            message += c.execute(taskList, ui, storage);
        } catch (RoodyException e) {
            message += e.getMessage();
        }
        return message;
    }

    /**
     * Runs Roody's main process.
     * @param args Args.
     */
    public static void main(String[] args) {
        Application.launch(RoodyMain.class, args);
    }
}
