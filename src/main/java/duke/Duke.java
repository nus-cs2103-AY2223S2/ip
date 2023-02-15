package duke;

import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke is a program that helps users keep track of their tasks
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * constructor for Duke
     * 
     * @param filePath string for path-to-file to read/write list of tasks from/to
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * gets a response to be displayed on the UI depending on the user's input
     *
     * @param input user input
     * @return the output to be displayed by Duke
     */
    public String getResponse(String input) {
        String response = "";
        if (!input.equals("bye")) {
            try {
                response = Parser.parse(input, tasks);
                storage.save(tasks);
            } catch (DukeException e) {
                response = e.toString();
            }
        } else {
            try {
                response = Parser.parse(input, tasks);
            } catch (DukeException e) {
                response = e.toString();
            }
        }
        return response;
    }
}
