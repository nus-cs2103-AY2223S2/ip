package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A UnmarkCommand class that encapsulates the actions of changing the status
 * of a Task to be not done.
 */

public class UnmarkCommand extends Command {
    private final int taskIndex;
    private static final String TASK_LIST_EMPTY_MESSAGE = "OOPS!!! Your task list is currently empty";
    private static final String INVALID_INDEX_MESSAGE = "OOPS!!! The input index is not within the range of [1, ";
    private static final String UNMARKED_TASK_MESSAGE = "OK, I've marked this task as not done yet:\n ";

    /**
     * Constructor of UnmarkCommand that takes in the index of the task to unmarked.
     *
     * @param taskIndex The index of the task to be marked
     */
    public UnmarkCommand(int taskIndex) {
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
     * Checks whether the index is valid with respect to the given list.
     *
     * @param list The given list to be checked
     * @return Whether the given
     */
    public boolean isValidIndex(TaskList list) {
        return taskIndex >= 0 && taskIndex < list.getNoOfTasks();
    }

    /**
     * Marks the list with the given index as not done.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     * @throws DukeException Throws exception if the list is empty or the given index is our of range
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, CommandHistory commandHistory) throws DukeException {
        commandHistory.saveState(tasks);
        if (isEmpty(tasks)) {
            String errorMessage = TASK_LIST_EMPTY_MESSAGE + "\nPlease add in more tasks";
            throw new InvalidInputException(errorMessage);
        }
        if (!isValidIndex(tasks)) {
            String errorMessage = INVALID_INDEX_MESSAGE + tasks.getNoOfTasks() + "]";
            errorMessage += "\nPlease input a valid index";
            throw new InvalidInputException(errorMessage);
        } else {
            DukeTask currentTask = tasks.getTask(this.taskIndex);
            currentTask.unmark();
            String message = UNMARKED_TASK_MESSAGE + currentTask;
            ui.appendResponse(message);
        }
        storage.saveTaskList(tasks);
    }
}
