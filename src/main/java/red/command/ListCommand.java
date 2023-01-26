package red.command;

import red.storage.Storage;
import red.task.Task;
import red.task.TaskList;
import red.ui.UI;

/**
 * A ListCommand class that encapsulates the action of displaying all the tasks on the TaskList.
 */

public class ListCommand extends Command {
    /**
     * Displays all the tasks with their respective types and status.
     *
     * @param tasks   The user TaskList that contains all the task to be manipulated
     * @param ui      The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        StringBuilder listContent = new StringBuilder("Here are the tasks in your list:\n");
        if (tasks.getTaskListSize() == 0) {
            System.out.println("There are no tasks in your list.");
            return;
        }
        System.out.println(tasks.toString());
    }
}
