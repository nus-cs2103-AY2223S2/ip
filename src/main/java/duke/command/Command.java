package duke.command;

import java.util.HashMap;
import java.util.Map;

import duke.model.TaskList;

/**
 * Abstract implementation of a {@code Command} in the application.
 */
public abstract class Command {

    protected Map<String, String> arguments;

    protected Command() {
        this(new HashMap<>());
    }

    protected Command(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes this {@code Command}.
     *
     * @param list the {@code TaskList} that this {@code Command} may operate on
     * @return the feedback to the user as a string
     */
    public abstract String execute(TaskList list);

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
