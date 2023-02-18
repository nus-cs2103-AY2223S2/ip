package duke.commands;

/**
 * Abstract class representing a Command to be executed.
 */
public abstract class Command {
    /**
     * Executes the implemented <code>Command</code>.
     * @return The string output result of execution.
     */
    public abstract String execute();
}
