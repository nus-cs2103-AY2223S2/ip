package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import tasktypes.Task;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public boolean isGoodbye() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.addTask(task);
    }
}
