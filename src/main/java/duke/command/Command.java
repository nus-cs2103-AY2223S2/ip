package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

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
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if command is an exit command
     *
     * @return boolean stating if command is an exit command
     */
    public boolean isExit() {
        return false;
    }
}
