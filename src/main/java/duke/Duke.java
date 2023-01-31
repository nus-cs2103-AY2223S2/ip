package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke class represents an instance of a Duke.
 *
 * @author Tan Wei Shwin, Linus
 * @version 0.1
 */
public class Duke {
    /** Storage of Duke */
    private Storage storage;
    /** TaskList of Duke */
    private TaskList tasks;
    /** Ui of Duke */
    private Ui ui;

    /**
     * Constructs Duke class.
     *
     * @param filePath Filepath of TaskList.
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.showStartUp();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Starts Duke application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.ser").run();
    }
}
