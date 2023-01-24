package twofive.command;

import twofive.data.TaskList;
import twofive.exception.InvalidTaskException;
import twofive.storage.Storage;
import twofive.ui.Ui;

/**
 * Lists all tasks added when command is executed.
 */
public class ListCommand extends Command {
    /**
     * Prints out all tasks added given a task list.
     *
     * @param tasks List of all current tasks.
     * @param ui UI interacting with user.
     * @param storage Storage for saving or loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(tasks.getTasksList());
    }
}
