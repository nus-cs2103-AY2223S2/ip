package command;

import smartduke.DukeException;
import smartduke.TaskList;
import smartduke.Ui;
import task.Task;

public class MarkCommand extends Command {
    private int taskNo;

    public MarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task markedTask = tasks.mark(this.taskNo);
        ui.showSuccess("ok i've marked this task as done:");
        ui.showSuccess(markedTask.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
