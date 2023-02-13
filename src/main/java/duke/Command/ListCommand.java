package duke.Command;

import duke.Utilities.Storage;
import duke.Utilities.TaskList;
import duke.Utilities.UI;

/**
 * The List command which is executed by duke.Utilities.Duke.
 */
public class ListCommand extends Command {

    /**
     * Print out the current list of tasks in the task list.
     * @param tasks The task list to add to/edit/delete from.
     * @param ui The UI to display the confirmation messages/error messages to users.
     * @param storage The storage which to store to when a task is added/deleted or its status is changed.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return tasks.printTaskList();
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
