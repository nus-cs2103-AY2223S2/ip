package berry.command;

import berry.exception.BerryException;
import berry.storage.Storage;
import berry.task.TaskList;
import berry.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {

    private boolean isExit;

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Sets isExit to true or false.
     *
     * @param isExit is the exit status of the command.
     */
    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command and returns the result.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BerryException;
}
