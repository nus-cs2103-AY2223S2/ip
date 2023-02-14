package duke.command;

import duke.DukeException;

/**
 * Represents a command that the user can input.
 *
 * @author wz2k
 */
public abstract class Command {
    /** Full command input by the user */
    protected String commandMessage;

    /**
     * Creates command based of the specified command message.
     *
     * @param commandMessage User's input.
     */
    public Command(String commandMessage) {
        this.commandMessage = commandMessage;
    }

    /**
     * Performs the command and returns the relevant reply.
     *
     * @return Taskbot reply to the command.
     */
    public abstract String execute();

    /**
     * Checks if the input arguments are valid.
     *
     * @throws DukeException If arguments are not valid.
     */
    public abstract void checkArguments() throws DukeException;
}
