package command;

import smartduke.DukeException;
import smartduke.TaskList;
import smartduke.Ui;
import task.Task;

public class UnmarkCommand extends Command {
    private int taskNo;

    public UnmarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task unmarkedTask = tasks.unmark(this.taskNo);
        ui.showSuccess("ok i've marked this task as not done yet");
        ui.showSuccess(unmarkedTask.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
