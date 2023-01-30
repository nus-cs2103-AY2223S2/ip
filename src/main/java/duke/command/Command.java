package duke.command;

import duke.DukeException;

public abstract class Command {

    /**
     * Executes this command and returns true if Duke should exit
     * @return Response from Duke
     * @throws DukeException Thrown on exception caught mid-execution.
     */
    public abstract String execute() throws DukeException;

    /**
     * Returns if this command should exit Duke
     * @return True if this command should shut down Duke
     */
    public boolean shouldTerminate() {
        return false;
    }
}
