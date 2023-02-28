package fea.commands;

import fea.exceptions.FeaException;
import fea.storage.Storage;
import fea.tasklist.TaskList;
import fea.ui.Ui;

/**
 * ByeCommand class that implements the Command interface.
 */
public class ByeCommand implements Command {
    /**
     * Prints the bye message.
     *
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @return String The string that contains the bye message.
     * @throws FeaException Exception should not be thrown in this function in this class.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return Ui.BYE;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
