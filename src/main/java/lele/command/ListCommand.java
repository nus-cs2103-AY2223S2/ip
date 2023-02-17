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
     * @param taskList Current task list instance.
     * @param ui Current ui instance.
     * @param storage Current storage instance.
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
