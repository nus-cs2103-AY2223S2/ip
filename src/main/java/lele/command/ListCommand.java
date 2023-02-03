package lele.command;

import lele.storage.Storage;
import lele.task.TaskList;
import lele.ui.Ui;

/**
 * Handles action to be taken when a user
 * queries their list.
 */
public class ListCommand extends Command {

    /**
     * Prints the tasks in the task list.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return Output to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printTaskList(taskList);
    }

    /**
     * List query will not terminate the program.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
