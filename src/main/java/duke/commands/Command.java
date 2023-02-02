package duke.commands;

import duke.exceptions.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

/**
 * The abstract {@code Command} class that all other {@code *Command} classes inherit from.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList The list of tasks.
     * @param ui       The ui manager.
     * @param storage  The storage manager.
     * @throws DukeException If the command cannot be executed.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks whether this {@code Command} is the {@code bye} command.
     *
     * @return A boolean indicating whether the command is the {@code bye} command.
     */
    public abstract boolean isByeCommand();
}
