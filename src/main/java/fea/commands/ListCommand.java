package fea.commands;

import fea.exceptions.FeaException;
import fea.storage.Storage;
import fea.tasklist.TaskList;
import fea.ui.Ui;
/**
 * ListCommand class that implements the Command interface.
 */

public class ListCommand implements Command {

    /**
     * Retrieves the current task list.
     *
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @return String The string that contains the tasks in the list.
     * @throws FeaException Exception should not be thrown in this function in this class.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FeaException {
        if (tasks.size() == 0) {
            return Ui.NO_TASKS;
        } else {
            return ui.printTasks(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
