package command;

import duke.DukeException;
import task.Task;
import task.TaskList;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskNo;

    /**
     * Constructor for DeleteCommand.
     * @param taskNo The task number of the task to delete.
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Deletes the task with the task no.
     * @param tasks The existing task list.
     * @throws DukeException If there are no task with the task number, i.e. the task to be
     *     deleted does not exist.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task removedTask = tasks.delete(this.taskNo);
        return "Noted. I've removed this task:\n\n" + removedTask + "\n\n"
                + "Now there are " + tasks.getNoOfTasks() + " tasks in your list.";
    }

    /**
     * Determines if the current command is an exit command.
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
