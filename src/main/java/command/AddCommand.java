package command;

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
     */
    @Override
    public String execute(TaskList tasks) {
        tasks.add(this.task);
        return "Got it. I've added this task:\n\n" + this.task + "\n\n"
                + "Now there are " + tasks.getNoOfTasks()
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
