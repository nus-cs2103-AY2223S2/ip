package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulation of a command.
 */
public abstract class Command {
    /**
     * Executes the command given.
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;

    /**
     * Returns whether the type of command is 'bye' or not.
     */
    public abstract boolean isExit();
}
