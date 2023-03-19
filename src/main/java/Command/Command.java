package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command from the user.
 */
public abstract class Command {
    /**
     * Checks if command is an exit command.
     *
     * @return A boolean value indicating whether it is an exit command.
     */
    public abstract boolean isExit();

    /**
     * Executes the command.
     *
     * @param tasks A TaskList containing the set of task the user has.
     * @param storage A Storage enabling Duke to store memory.
     * @return String The String message indicating status of action.
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
