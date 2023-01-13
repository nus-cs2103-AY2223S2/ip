package duke.instruction;

import duke.exception.GeneralDukeException;
import duke.exception.InvalidInputException;
import duke.task.GeneralDukeTask;
import duke.task.TaskList;

/**
 * A MarkAsDoneInstruction class that encapsulates the actions of changing the status
 * of a Task to be done.
 */

public class MarkAsDoneInstruction extends GeneralDukeInstruction{
    private final int taskIndex;

    /**
     * Constructor of MarkAsDoneInstruction that takes in the index of the task to marked.
     *
     * @param taskIndex The index of the task to be marked
     */
    public MarkAsDoneInstruction(int taskIndex) {
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
     * Checks whether the index is valid with respect to the given list.
     *
     * @param list The given list to be checked
     * @return Whether the given index is valid
     */
    public boolean isValidIndex(TaskList list) {
        return taskIndex >= 0 && taskIndex <= list.remainingTasks();
    }

    /**
     * Checks whether the index is valid with respect to the given list.
     *
     * @param list The user TaskList that contains all the task to be manipulated
     * @throws GeneralDukeException Throws exception if the list is empty
     * or the given index is our of range
     */
    @Override
    public void run(TaskList list) throws GeneralDukeException {
        if (isEmpty(list)) {
            String errorMessage = "☹ OOPS!!! Your task list is currently empty";
            throw new InvalidInputException(errorMessage + "\nPlease add in more tasks");
        } if (!isValidIndex(list)) {
            String errorMessage = "☹ OOPS!!! The input index is not within the range of [1, "
                    + list.remainingTasks() + "]";
            throw new InvalidInputException(errorMessage + "\nPlease input a valid index");
        } else {
            GeneralDukeTask currentTask = list.getTask(this.taskIndex);
            currentTask.markAsDone();
            format.displayWithBar("Nice! I've marked this task as done:\n " +
                    currentTask);
        }
    }
}
