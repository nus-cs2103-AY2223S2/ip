package command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task removedTask = tasks.delete(this.taskNo);
        ui.showSuccess("Noted. I've removed this task:");
        ui.showSuccess(removedTask.toString());
        ui.showSuccess("Now there are " + tasks.getNoOfTasks() + " tasks in your list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
