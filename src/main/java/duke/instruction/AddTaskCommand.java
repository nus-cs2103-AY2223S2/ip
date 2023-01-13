package duke.instruction;

import duke.exception.DukeException;
import duke.task.DukeTask;
import duke.task.TaskList;

/**
 * A more specific instruction class that encapsulates the action of adding a task
 * into the given TaskList.
 */

public abstract class AddTaskCommand extends Command {
    private final DukeTask task;

    /**
     * The constructor of AddTaskCommand that takes in the task to be added.
     *
     * @param task The task to be added
     */
    public AddTaskCommand(DukeTask task) {
        this.task = task;
    }

    /**
     * Adds the given task to the TaskList and display relevant information with the customized format.
     *
     * @param list The user TaskList that contains all the task to be manipulated
     * @throws DukeException Throws exceptions when instruction are not in the standard format
     */
    @Override
    public void run(TaskList list) throws DukeException {
        list.addTask(task);
        String message = "Got it. I've added this task:\n " + task
                + "\nNow you have " + list.remainingTasks() + " tasks in the list.";
        format.displayWithBar(message);
    }
}
