package sebastian.command;

import sebastian.exceptions.SebastianException;
import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

/**
 * Class used to handle a command
 */
public abstract class Command {

    /**
     * Execute the user command
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @return a string representing the result of task execution
     * @throws SebastianException when error / exception occurs during the execution
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws SebastianException;

    public boolean isExit() {
        return false;
    }
}
