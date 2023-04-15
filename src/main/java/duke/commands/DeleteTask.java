package duke.commands;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

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
