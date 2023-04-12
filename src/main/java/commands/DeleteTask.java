package commands;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public class DeleteTask implements Command {
    private int taskIndex;

    public DeleteTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removed = tasks.remove(taskIndex - 1);
        } catch (Exception e) {
            ui.showErrorMessage(e);
        }
        ui.showDelete();
    }

    public boolean isExit() {
        return false;
    }
}
