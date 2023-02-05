package duke.command;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
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
     * Returns String representation of operation that was conducted.
     *
     * @param tasks Tasklist object.
     * @param storage Storage object.
     * @param ui Ui Object.
     * @return String representation of operation conducted.
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;
}
