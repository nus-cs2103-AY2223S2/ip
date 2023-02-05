package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.parser.ErrorMessage;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            // throw an error message if task list is empty
            String errorMessage = ErrorMessage.TASK_LIST_EMPTY_ERROR + ErrorMessage.ADD_MORE_TASKS;
            throw new InvalidInputException(errorMessage);
        }

        if (!isValidIndex(tasks)) {
            // throw an error message if the input index is invalid
            String errorMessage = String.format(ErrorMessage.INVALID_INDEX_ERROR, tasks.getNoOfTasks());
            throw new InvalidInputException(errorMessage);
        } else {
            // get the task at the input index and unmark it
            DukeTask currentTask = tasks.getTask(this.taskIndex);
            currentTask.unmark();
            // construct success message
            String message = UNMARKED_TASK_MESSAGE + currentTask;
            ui.appendResponse(message);
        }

        // save the updated task list
        storage.saveTaskList(tasks);
    }
}
