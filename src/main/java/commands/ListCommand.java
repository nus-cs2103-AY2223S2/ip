package commands;

import files.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Command which lets Duke list out all the tasks currently in the task list.
 */
public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the procedure of listing all tasks into the task list.
     * @param taskList task list to traverse and list all tasks
     * @param ui user interface
     * @param storage storage for reading and writing data to files
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.listItems();
    }
}
