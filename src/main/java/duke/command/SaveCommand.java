package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A command to save all current tasks to the disk.
 * @author Junyi
 */
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
    public String execute() throws DukeException {
        try {
            storage.save(taskList);
            return ui.showSavedDataMessage();
        } catch (IOException e) {
            return "Arii can't access your files... Fix your system first.";
        }
    }
}
