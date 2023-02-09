package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Gives command to mark item as complete
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Initialises mark class
     *
     * @param index task sequences in task list
     */
    public MarkCommand(int index) {
        this.index = index;
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
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return taskList.markItem(index);
    }
}
