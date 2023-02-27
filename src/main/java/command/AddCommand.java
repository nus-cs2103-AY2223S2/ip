package command;

import duke.DukeException;
import task.Task;
import task.TaskList;

/**
 * Command to add a task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand.
     * @param task The task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the existing task list.
     * @param tasks The existing task list.
     * @return The return status of the result from executing this command in the form of a text message.
     */
    @Override
    public String execute(TaskList tasks) {
        tasks.add(this.task);
        Command.addPrevCommand(this);
        return "Got it. I've added this task:\n\n" + this.task + "\n\n"
                + "Now there are " + tasks.getNoOfTasks()
                + " tasks in your list.";
    }

    /**
     * Deletes the task that was added by this command.
     * @param tasks The list of tasks that contains the added task.
     * @return The return status of the result from deleting the previous added task
     *     in the form of a text message.
     * @throws DukeException If deleting the previously added task throws a DukeException.
     */
    @Override
    String undo(TaskList tasks) throws DukeException {
        /* Delete the previously added task */
        Task prevAddedTask = tasks.delete(tasks.getNoOfTasks());

        return "Ok. I have deleted the task you previously added:\n\n" + prevAddedTask
                + "\n\n" + "Now there are " + tasks.getNoOfTasks()
                + " tasks in your list.";
    }

    /**
     * Determines if the current command is an exit command.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
