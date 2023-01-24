package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * Check whether the given list is empty.
     *
     * @param list The given list to be checked
     * @return Whether the given list is empty
     */
    public boolean isEmpty(TaskList list) {
        return list.getNoOfTasks() == 0;
    }

    /**
     * Checks whether the index is valid with respect to the given list
     *
     * @param list The given list to be checked
     * @return Whether the given index is valid.
     */
    public boolean isValidIndex(TaskList list) {
        return this.taskIndex >= 0 && this.taskIndex < list.getNoOfTasks();
    }

    /**
     * Remove a particular task with the given index in the TaskList and display
     * the relevant information of the task and the remaining TaskList.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     * @throws DukeException Throws Exception when the user inputs invalid instruction
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, CommandHistory commandHistory) throws DukeException {
        final String TASK_LIST_IS_EMPTY_ERROR = "OOPS!!! Your task list is currently empty\nPlease add in more tasks";
        final String INVALID_INDEX_ERROR = "OOPS!!! The input index is not within the range of [1, %d]\nPlease input a valid index";

        commandHistory.saveState(tasks);
        if (isEmpty(tasks)) {
            throw new InvalidInputException(TASK_LIST_IS_EMPTY_ERROR);
        }
        if (!isValidIndex(tasks)) {
            throw new InvalidInputException(String.format(INVALID_INDEX_ERROR, tasks.getNoOfTasks()));
        } else {
            DukeTask deletedTask = tasks.deleteTask(this.taskIndex);
            String message = String.format("Noted. I've removed this task:\n %s \nNow you have %d tasks in the list.", deletedTask.toString(), tasks.getNoOfTasks());
            ui.appendResponse(message);
        }
        storage.saveTaskList(tasks);
    }

}
