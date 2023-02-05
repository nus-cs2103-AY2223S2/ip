package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A ListCommand class that encapsulates the action of displaying all the tasks on the TaskList.
 */

public class ListCommand extends Command {
    private final static String NO_TASKS_MESSAGE = "There are no tasks in your list.";
    private final static String TASK_LIST_MESSAGE = "Here are the tasks in your list:\n";
    /**
     * Displays all the tasks with their respective types and status.
     *
     * @param tasks   The user TaskList that contains all the task to be manipulated
     * @param ui      The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // check if there are no tasks
        if (tasks.getNoOfTasks() == 0) {
            ui.appendResponse(NO_TASKS_MESSAGE);
            return;
        }

        // append the final task list to the UI
        ui.appendResponse(TASK_LIST_MESSAGE + tasks.toString());
    }
}