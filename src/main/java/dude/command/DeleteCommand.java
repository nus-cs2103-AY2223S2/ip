package dude.command;

import dude.task.Task;
import dude.task.TaskList;
import dude.storage.Storage;
import dude.ui.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (Task.count >= taskIndex && Task.count != 0) {
            Task currentTask = tasks.getTask(taskIndex);
            tasks.deleteTask(taskIndex);
            storage.saveData(tasks);
            ui.showDelete(currentTask);
        } else {
            ui.showError("\tUhh... Where got this task for me to delete?");
        }
    }
}
