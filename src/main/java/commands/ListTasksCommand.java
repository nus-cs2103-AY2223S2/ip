package commands;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * This class is to list all tasks in supplied tasklist
 */
public class ListTasksCommand extends Command {

    /**
     * Lists all tasks in specified tasklist
     *
     * @param taskList the list containing the tasks to list
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     * @return {@inheritDoc}
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.getSizeAsString() + taskList.getAllAsString();
    }
}
