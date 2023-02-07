package duke.command;

import duke.TaskList;
import duke.exception.DukeRuntimeException;

/**
 * Abstract implementation of a {@code Command} in the application.
 */
public abstract class Command {

    /**
     * Executes this {@code Command}.
     *
     * @param list the {@code TaskList} that this {@code Command} may operate on
     * @return a {@code CommandResult} containing the feedback to the user
     */
    public CommandResult execute(TaskList list) {
        try {
            String msg = tryExecute(list);
            return new CommandResult(msg);
        } catch (DukeRuntimeException ex) {
            return new CommandResult(ex.getMessage());
        }
    }

    /**
     * Tries to execute this {@code Command}.
     * 
     * @param list the {@code TaskList} that this {@code Command} may operate on
     * @return the feedback to the user as a string
     * @throws DukeRuntimeException if the execution failed
     */
    abstract String tryExecute(TaskList list);

    /**
     * Checks whether this {@code Command} signals the exit of the application.
     *
     * @return {@code true} if this {@code Command} is {@code ExitCommand}
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
