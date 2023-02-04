package command;

import duke.DukeException;
import task.Task;
import task.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int taskNo;

    /**
     * Constructor for MarkCommand.
     * @param taskNo The task number of the task to be marked.
     */
    public MarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Marks the task with the task no as done.
     * @param tasks The existing task list.
     * @throws DukeException If there are no task with the task number, i.e. the task to be
     *     marked does not exist.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task markedTask = tasks.mark(this.taskNo);
        return "ok i've marked this task as done:\n\n" + markedTask;
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
