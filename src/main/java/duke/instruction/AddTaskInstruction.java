package duke.instruction;

import duke.exception.GeneralDukeException;
import duke.task.GeneralDukeTask;
import duke.task.TaskList;

/**
 * A more specific instruction class that encapsulates the action of adding a task
 * into the given TaskList.
 */

public abstract class AddTaskInstruction extends GeneralDukeInstruction {
    private final GeneralDukeTask task;

    /**
     * The constructor of AddTaskInstruction that takes in the task to be added.
     *
     * @param task The task to be added
     */
    public AddTaskInstruction(GeneralDukeTask task) {
        this.task = task;
    }

    /**
     * Adds the given task to the TaskList and display relevant information with the customized format.
     *
     * @param list The user TaskList that contains all the task to be manipulated
     * @throws GeneralDukeException Throws exceptions when instruction are not in the standard format
     */
    @Override
    public void run(TaskList list) throws GeneralDukeException {
        list.addTask(task);
        String message = "Got it. I've added this task:\n " + task
                + "\nNow you have " + list.remainingTasks() + " tasks in the list.";
        format.displayWithBar(message);
    }
}
