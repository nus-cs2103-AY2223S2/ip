package red.command;

import red.exception.RedException;
import red.storage.Storage;
import red.task.Task;
import red.task.TaskList;
import red.ui.UI;

/**
 * A "delete" instruction that remove a particular task with the given index in the TaskList. `
 */

public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructor for DeleteCommand that takes in the index of the task to be deleted.
     *
     * @param taskIndex the index of the task to be deleted
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Remove a particular task with the given index in the TaskList and display
     * the relevant information of the task and the remaining TaskList.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RedException {
        final String TASK_LIST_IS_EMPTY_ERROR = "OOPS!!! Your task list is currently empty\nPlease add in more tasks";
        final String INVALID_INDEX_ERROR = "OOPS!!! The input index is not within the range of [1, %d]\nPlease input a valid index";

        if (tasks.getTaskListSize() < 1) {
            throw new RedException(TASK_LIST_IS_EMPTY_ERROR);
        }
        if (tasks.getTaskListSize() < this.taskIndex) {
            throw new RedException(String.format(INVALID_INDEX_ERROR, tasks.getTaskListSize()));
        } else {
            Task deletedTask = tasks.deleteTask(this.taskIndex);
            String message = String.format("Noted. I've removed this task:\n %s \nNow you have %d tasks in the list.", deletedTask.toString(), tasks.getTaskListSize());
        }
    }

}