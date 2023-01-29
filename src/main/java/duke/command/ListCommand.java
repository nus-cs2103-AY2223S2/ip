package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList);
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
