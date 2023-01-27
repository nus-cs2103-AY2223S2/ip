package duke.command;

import duke.exceptions.TaskException;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Gives command to unmark item as incomplete
 */
public class UnmarkCommand extends Command {
    private int index;
    private boolean exit;

    /**
     * Initialises unmark class
     *
     * @param index task sequences in task list
     */
    public UnmarkCommand(int index) {
        this.index = index;
        this.exit = false;
    }

    /**
     * Exits duke if it detects bye command
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return exit;
    }

    /**
     * Unmarks task items
     * @param taskList arraylist that stores tasks
     * @param storage  stores data of tasks
     * @param ui       responds to user input
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.unmarkItem(index);
    }
}
