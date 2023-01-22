package duke.command;

import duke.DukeException;

public abstract class Command {

    /**
     * Executes this command and returns true if Duke should exit
     * @return True if Duke should exit.
     * @throws DukeException Thrown on exception caught mid-execution.
     */
    public abstract boolean execute() throws DukeException;
}
