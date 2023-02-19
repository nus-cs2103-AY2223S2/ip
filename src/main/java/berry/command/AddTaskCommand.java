package berry.command;

import berry.exception.BerryException;
import berry.storage.Storage;
import berry.task.Task;
import berry.task.TaskList;
import berry.ui.Ui;


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
        String output = ui.showAdd() + "\t" + tasks.addTask(task);
        output += "\n" + tasks.getNumberOfTasks();
        storage.saveTasks(tasks);
        return output;
    }
}


