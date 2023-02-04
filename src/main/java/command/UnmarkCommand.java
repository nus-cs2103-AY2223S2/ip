package command;

import duke.DukeException;
import task.Task;
import task.TaskList;

/**
 * Command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private int taskNo;

    /**
     * Constructor for Unmark Command.
     * @param taskNo The task number of the task to be unmarked.
     */
    public UnmarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Unmarks the task with the task no as done.
     * @param tasks The existing task list.
     * @throws DukeException If there are no task with the task number, i.e. the task to be
     *     unmarked does not exist.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task unmarkedTask = tasks.unmark(this.taskNo);
        return "ok i've marked this task as not done yet:\n\n" + unmarkedTask;
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
