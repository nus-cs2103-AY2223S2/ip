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
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
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
        ui.showWelcome();
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

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
