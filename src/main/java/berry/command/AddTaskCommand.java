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

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        ui.showAdd();
        tasks.addTask(task);
        storage.saveTasks(tasks);
    }
}


