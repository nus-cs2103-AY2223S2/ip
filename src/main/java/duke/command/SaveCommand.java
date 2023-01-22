package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;

public class SaveCommand extends Command {

    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructor for SaveCommand.
     * Saves the tasks to local storage.
     * @param taskList TaskList of Duke's tasks.
     * @param storage Storage instance of Duke.
     * @param ui Ui instance of Duke.
     */
    public SaveCommand(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    @Override
    public boolean execute() throws DukeException {
        try {
            storage.save(taskList);
            ui.showSavedDataMessage();
        } catch (IOException e) {
            throw new DukeException("Arii can't access your files... Fix your system first.");
        }

        return false;
    }
}
