package duke.command;

import duke.exceptions.TaskException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Gives command to delete item
 */
public class UpdateCommand extends Command {
    private int index;
    private final String input;

    /**
     * Initialises delete class
     *
     * @param index task sequences in task list
     */
    public UpdateCommand(int index, String input) {
        this.index = index;
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws TaskException {
        return taskList.updateTask(index, input);
    }
}
