package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * The List command which is executed by Duke.
 */
public class ListCommand extends Command {

    /**
     * Print out the current list of tasks in the task list.
     * @param tasks The task list to add to/edit/delete from.
     * @param ui The UI to display the confirmation messages/error messages to users.
     * @param storage The storage which to store to when a task is added/deleted or its status is changed.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showConfirmation(tasks.printTaskList());
    }
}
