package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
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
     *
     * @param taskList arraylist that stores tasks
     * @param storage  stores data of tasks
     * @param ui       responds to user input
     * @return instruction successfully set
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {

        return taskList.unmarkItem(index);
    }
}
