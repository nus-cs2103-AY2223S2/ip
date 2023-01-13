package duke.instruction;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.task.TaskList;

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
        return list.remainingTasks() == 0;
    }

    /**
     * Checks whether the index is valid with respect to the given list
     *
     * @param list The given list to be checked
     * @return Whether the given index is valid.
     */
    public boolean isValidIndex(TaskList list) {
        return taskIndex >= 0 && taskIndex < list.remainingTasks();
    }

    /**
     * Remove a particular task with the given index in the TaskList and display
     * the relevant information of the task and the remaining TaskList.
     *
     * @param list The user TaskList that contains all the task to be manipulated
     * @throws DukeException Throws exception if the list is empty
     * or the given index is our of range
     */
    @Override
    public void run(TaskList list) throws DukeException {
        if (isEmpty(list)) {
            String errorMessage = "☹ OOPS!!! Your task list is currently empty";
            throw new InvalidInputException(errorMessage + "\nPlease add in more tasks");
        }
        if (!isValidIndex(list)) {
            String errorMessage = "☹ OOPS!!! The input index is not within the range of [1, "
                    + list.remainingTasks() + "]";
            throw new InvalidInputException(errorMessage + "\nPlease input a valid index");
        } else {
            format.displayWithBar("Noted. I've removed this task:\n " +
                    list.getTask(taskIndex) + "\nNow you have " +
                    (list.remainingTasks() - 1) + " tasks in the list.");
            list.deleteTask(this.taskIndex);
        }
    }
}
