package panav.command;

import panav.exception.DukeException;
import panav.storage.Storage;
import panav.task.TaskList;
import panav.ui.Ui;

/**
 * Abstract class to encapsulate a command.
 */
public abstract class Command {


    /**
     * Abstract method to denote behaviour of each command when called.
     * @param tasks the list of tasks.
     * @param ui ui to interact with user.
     * @param storage storage to read/write text in file.
     * @throws DukeException to catch any possible exception.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Method to check if the command is 'bye' or not.
     * @return default value is false, unless it is 'bye' command
     */
    public boolean isExit() {
        return false;
    }
}
