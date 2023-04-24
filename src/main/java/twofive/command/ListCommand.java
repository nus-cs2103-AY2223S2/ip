package twofive.command;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.ui.TaskContainer;

/**
 * Lists all tasks added when command is executed.
 */
public class ListCommand extends Command {
    /**
     * Prints out all tasks added given a task list.
     *
     * @param tasks List of all current tasks.
     * @param storage Storage for saving or loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskContainer.setTasks(tasks.getTasks());
        return "Here is your list of tasks added";
    }
}
