package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isEmpty(tasks)) {
            String errorMessage = "OOPS!!! Your task list is currently empty";
            throw new InvalidInputException(errorMessage + "\nPlease add in more tasks");
        }
        if (!isValidIndex(tasks)) {
            String errorMessage = "OOPS!!! The input index is not within the range of [1, "
                    + tasks.getNoOfTasks() + "]";
            throw new InvalidInputException(errorMessage + "\nPlease input a valid index");
        } else {
            String message = "Noted. I've removed this task:\n "
                    + tasks.getTask(this.taskIndex) + "\nNow you have "
                    + (tasks.getNoOfTasks() - 1) + " tasks in the list.";
            ui.appendResponse(message);
            tasks.deleteTask(this.taskIndex);
        }
        storage.saveTaskList(tasks);
    }
}
