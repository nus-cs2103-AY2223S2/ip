package dude.command;

import dude.task.Task;
import dude.task.TaskList;
import dude.storage.Storage;
import dude.ui.Ui;

public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTask);
        storage.saveData(tasks);
        ui.showAdd(newTask);
    }
}
