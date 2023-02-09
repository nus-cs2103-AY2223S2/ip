package berry.command;

import berry.task.Task;
import berry.task.TaskList;
import berry.ui.Ui;
import berry.storage.Storage;
import berry.exception.BerryException;


/**
 * Adds a task to the task list.
 */
public class AddTaskCommand extends Command {

    /** The task to be added */
    private final Task task;

    public AddTaskCommand(Task taskToAdd) {
        this.task = taskToAdd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        String output = ui.showAdd() + tasks.addTask(task);
        storage.saveTasks(tasks);
        return output;
    }
}


