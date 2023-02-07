package duke.command;

import duke.TaskList;

/**
 * Abstract implementation of a {@code Command} in the application.
 */
public abstract class Command {

    /**
     * Executes this {@code Command}.
     *
     * @param list the {@code TaskList} that this {@code Command} may operate on.
     */
    public abstract CommandResult execute(TaskList list);

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
