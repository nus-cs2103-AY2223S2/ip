package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Abstract representation of a Command.
 */
public abstract class Command {
    /**
     * Returns true if command is an Exit Command.
     *
     * @return Boolean value.
     */
    public abstract boolean isGoodbye();

    /**
     * Executes the command logic.
     *
     * @param tasks Tasklist object.
     * @param storage Storage object.
     * @param ui Ui Object.
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui);
}
