package dude.command;

import dude.storage.Storage;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Command structure for all commands.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Returns value of isExit.
     *
     * @return Value of isExit to check if it is an exit command.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets value of isExit.
     *
     * @param isExit Boolean flag to check if it is an exit command.
     */
    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command.
     *
     * @param tasks   TaskList object to store a list of task.
     * @param ui      Ui object to communicate with user.
     * @param storage Storage object for data manipulation.
     * @return
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

}
