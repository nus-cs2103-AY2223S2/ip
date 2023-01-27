package command;

import shigure.Ui;
import storage.Storage;
import task.Task;
import task.TaskList;

public class AddTask implements Command {
    private final Task task;

    public AddTask(Task task) {
        this.task = task;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.print("Added this thing! That makes " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + ":");
        ui.print("  " + task.toString());
    }
}
