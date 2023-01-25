package berry.command;

import berry.task.Task;
import berry.task.TaskList;
import berry.ui.Ui;
import berry.storage.Storage;
import berry.exception.BerryException;


public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        ui.showAdd();
        tasks.addTask(task);
        storage.saveTasks(tasks);
    }
}

