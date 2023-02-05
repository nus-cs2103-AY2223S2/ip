package commands;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * An abstract class to represent the various commands.
 */
public abstract class Command {
    /**
     * Actions for the respective command.
     * @param tasks The database.
     * @param ui The user interface.
     * @param storage The storage.s
     * @return The reply for executing the command
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Check to continue the conversation.
     * @return boolean.
     */
    public abstract boolean isContinueConvo();
}
