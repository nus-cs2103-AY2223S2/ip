package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main class for Fake Duke the chat bot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class
     *
     * @param filePath File path of the local file for tasks storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(String.format("%sWill be creating a new task list instead~\n", e.getMessage()));
            tasks = new TaskList();
        }
    }

    /**
     * Provides the main skeleton code for Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
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

    //CHECKSTYLE.OFF: JavadocMethod
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
    //CHECKSTYLE.ON: JavadocMethod
}
