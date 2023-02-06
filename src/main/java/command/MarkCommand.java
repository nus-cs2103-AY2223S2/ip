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
     * @return The return status of the result from executing this command in the form of a text message.
     * @throws DukeException If there are no task with the task number, i.e. the task to be
     *     marked does not exist.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task markedTask = tasks.mark(this.taskNo);
        Command.addPrevCommand(this);
        return "ok i've marked this task as done:\n\n" + markedTask;
    }

    /**
     * Unmarks the task that was previously marked as done by this command.
     * @param tasks The list of tasks that contains the previously marked task.
     * @return The return status of the result from unmarking the previously marked task
     *     in the form of a text message.
     * @throws DukeException If unmarking the previously marked task throws a DukeException.
     */
    @Override
    String undo(TaskList tasks) throws DukeException {
        /* Unmark the command that was marked previously */
        Task prevMarkedTask = tasks.unmark(taskNo);

        return "Ok. I have unmarked the task you previously marked as done:\n\n" + prevMarkedTask;
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
