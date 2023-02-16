package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The main class of the Duke application, storing the ui, storage and task list.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializing the storage, ui and task list.
     * @param filePath the file path for the storage
     */
    public Duke(String filePath) {
        ui = new Ui();
        DukeException.setUi(ui);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readTaskList());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            return ui.getResponses();
        }
    }

    public Ui getUi() {
        return ui;
    }
}


