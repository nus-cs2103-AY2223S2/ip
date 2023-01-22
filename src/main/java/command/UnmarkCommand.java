package command;

import duke.DukeException;
import duke.Ui;
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
     * @param ui The ui of Duke chat.
     * @throws DukeException If there are no task with the task number, i.e. the task to be
     *     unmarked does not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task unmarkedTask = tasks.unmark(this.taskNo);
        ui.showSuccess("ok i've marked this task as not done yet");
        ui.showSuccess(unmarkedTask.toString());
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
