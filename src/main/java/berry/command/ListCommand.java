package berry.command;

import berry.task.TaskList;
import berry.ui.Ui;
import berry.storage.Storage;
import berry.exception.BerryException;

/**
 * Lists all the tasks in the task list.
 */
public class ListCommand extends Command{

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BerryException{
        return ui.showListOfTasks(tasks);
    }
}
