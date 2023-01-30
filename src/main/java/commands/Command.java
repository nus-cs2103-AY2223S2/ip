package commands;

import files.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a Command object for which Duke can use to execute operations.
 */
public abstract class Command {
    /**
     * Indicates to Duke whether to exit the program.
     * @return true if it is a terminal command, false otherwise
     */
    public abstract boolean isExit();
    /**
     * Executes the command.
     * @param taskList task list for command to operate on
     * @param ui ui for any response and interaction needed with user
     * @param storage storage for command to do IO operations on
     * @return String representation of list
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
