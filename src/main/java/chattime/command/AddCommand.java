package chattime.command;

import chattime.TaskList;
import chattime.storage.Storage;
import chattime.task.Task;
import chattime.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task addedTask) {
        task = addedTask;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.addTask(task);
        storage.saveToFile(task);
        ui.printAddTask(task, Task.printTotalTask());
    }

}
