package berry.command;

import berry.task.TaskList;
import berry.ui.Ui;
import berry.storage.Storage;
import berry.exception.BerryException;

/**
 * Lists all the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        return ui.showListOfTasks(tasks);
    }
}
