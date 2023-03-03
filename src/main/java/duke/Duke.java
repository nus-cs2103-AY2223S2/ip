package duke;

import java.io.IOException;

import duke.command.Command;

/**
 * Represents an instance of the Duke program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for an instance of Duke.
     * Contains a Ui for interacting with user,
     * Storage for saving and retrieving tasks from a file,
     * and Tasklist for storing the tasks.
     * Catches any errors thrown when the program is running.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.retrieveTasks());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showError(e);
        }
    }

    /**
     * Dictates program flow for Duke.
     * Catches any errors thrown when parsing user inputs.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } catch (IOException e) {
                ui.showError(e);
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            return response;
        } catch (DukeException e) {
            return ui.showError(e);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
