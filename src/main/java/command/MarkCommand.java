package command;

import duke.DukeException;
import duke.Ui;
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
     * @param ui The ui of Duke chat.
     * @throws DukeException If there are no task with the task number, i.e. the task to be
     *     marked does not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task markedTask = tasks.mark(this.taskNo);
        ui.showSuccess("ok i've marked this task as done:");
        ui.showSuccess(markedTask.toString());
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
