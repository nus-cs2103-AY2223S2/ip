package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.parser.ErrorMessage;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A "delete" instruction that remove a particular task with the given index in the TaskList. `
 */

public class DeleteCommand extends Command {
    private static final String TASK_REMOVED_MESSAGE = "Noted. I've removed this task:\n %s \n"
            + "Now you have %d tasks in the list.";
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
     * Checks whether the given list is empty.
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
     * @return Whether the given index is valid.
     */
    public boolean isValidIndex(TaskList list) {
        return this.taskIndex >= 0 && this.taskIndex < list.getNoOfTasks();
    }

    /**
     * Removes a particular task with the given index in the TaskList and display
     * the relevant information of the task and the remaining TaskList.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     * @throws DukeException Throws Exception when the user inputs invalid instruction
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isEmpty(tasks)) {
            //if the task list is empty, throw an exception
            String errorMessage = ErrorMessage.TASK_LIST_EMPTY_ERROR + ErrorMessage.ADD_MORE_TASKS;
            throw new InvalidInputException(errorMessage);
        }

        if (!isValidIndex(tasks)) {
            //if the index passed is not valid, throw an exception
            String errorMessage = String.format(ErrorMessage.INVALID_INDEX_ERROR, tasks.getNoOfTasks());
            throw new InvalidInputException(errorMessage);
        } else {
            //delete the task from the list, store the task that was deleted
            DukeTask deletedTask = tasks.deleteTask(this.taskIndex);
            //create a message that the task has been removed and the number of tasks remaining
            String message = String.format(TASK_REMOVED_MESSAGE, deletedTask.toString(), tasks.getNoOfTasks());
            //append the message to the UI
            ui.appendResponse(message);
        }

        //save the updated task list to storage
        storage.saveTaskList(tasks);
    }
}
