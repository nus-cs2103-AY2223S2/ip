package reborn.command;

import reborn.data.TaskList;
import reborn.data.exception.RebornException;
import reborn.storage.Storage;
import reborn.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command given by the user.
     *
     * @param tasks   to be modified
     * @param ui      to display changes
     * @param storage to interact with as necessary
     * @return Command execution string.
     * @throws RebornException
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws RebornException;

    /**
     * Checks if command is an exit command
     *
     * @return boolean stating if command is an exit command
     */
    public boolean isExit() {
        return false;
    }
}
