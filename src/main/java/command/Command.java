package command;

import duke.DukeException;

/**
 * Base class of all commands that duke recognises.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @return A string confirmation.
     * @throws DukeException Throws what went wrong.
     */
    public abstract String execute() throws DukeException;
}
