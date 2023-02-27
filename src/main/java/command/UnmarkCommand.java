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
     * @return The return status of the result from executing this command in the form of a text message.
     * @throws DukeException If there are no task with the task number, i.e. the task to be
     *     unmarked does not exist.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task unmarkedTask = tasks.unmark(this.taskNo);
        Command.addPrevCommand(this);
        return "ok i've marked this task as not done yet:\n\n" + unmarkedTask;
    }

    /**
     * Re-marks the task that was previously unmarked as done by this command.
     * @param tasks The list of tasks that contains the previously unmarked task.
     * @return The return status of the result from re-marking the previously unmarked task
     *     in the form of a text message.
     * @throws DukeException If re-marking the previously unmarked task throws a DukeException.
     */
    @Override
    public String undo(TaskList tasks) throws DukeException {
        /* Mark the command that was unmarked previously */
        Task prevUnmarkedTask = tasks.mark(taskNo);

        return "Ok. I have marked the task you previously unmarked as done:\n\n" + prevUnmarkedTask;
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
