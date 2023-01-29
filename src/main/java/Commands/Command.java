package commands;

import java.io.IOException;

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
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Check to continue the conversation.
     * @return boolean.
     */
    public abstract boolean isContinueConvo();
}
