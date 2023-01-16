package duke.command;

import java.io.IOException;

import duke.display.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
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
     * @param tasks The user TaskList that contains all the task to be manipulated
<<<<<<< HEAD
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     * @throws DukeException Throws Exception when the user inputs invalid instruction
=======
     * @throws DukeException Throws exception if the list is empty
     *     or the given index is our of range
>>>>>>> branch-A-CodingStandard
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (isEmpty(tasks)) {
            String errorMessage = "☹ OOPS!!! Your task list is currently empty";
            throw new InvalidInputException(errorMessage + "\nPlease add in more tasks");
        }
        if (!isValidIndex(tasks)) {
            String errorMessage = "☹ OOPS!!! The input index is not within the range of [1, "
                    + tasks.remainingTasks() + "]";
            throw new InvalidInputException(errorMessage + "\nPlease input a valid index");
        } else {
            ui.displayWithBar("Noted. I've removed this task:\n "
                    + tasks.getTask(taskIndex) + "\nNow you have "
                    + (tasks.remainingTasks() - 1) + " tasks in the list.");
            tasks.deleteTask(this.taskIndex);
        }
        storage.save(tasks);
    }
}
