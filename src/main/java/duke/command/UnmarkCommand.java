package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Gives command to unmark item as incomplete
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Initialises unmark class
     *
     * @param index task sequences in task list
     */
    public UnmarkCommand(int index) {
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

        return taskList.unmarkItem(index);
    }
}
