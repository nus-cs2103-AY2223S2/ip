package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * A ListCommand class that encapsulates the action of displaying all the tasks on the TaskList.
 */

public class ListCommand extends Command {
    /**
     * Displays all the tasks with their respective types and status.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder listContent = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.remainingTasks(); i++) {
            listContent.append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
        }

        String message = String.valueOf(listContent);
        ui.appendResponse(message);
    }
}
