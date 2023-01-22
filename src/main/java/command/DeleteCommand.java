package command;

import smartduke.DukeException;
import smartduke.TaskList;
import smartduke.Ui;
import task.Task;

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
