package duke.command;
import duke.Tasklist;

/**
 * Abstract class representing a {@code Command}.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasklist The current tasklist.
     * @param saveNo The current save number.
     *
     * @return The message shown to the user after executing.
     */
    public abstract String execute(Tasklist tasklist, int saveNo) throws Exception;

    /**
     * Determines if the command is an
     * exit command.
     *
     * @return Boolean for whether the command exits the application.
     */
    public abstract boolean isExit();
}

