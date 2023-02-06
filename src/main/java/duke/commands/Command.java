package duke.commands;

/**
 * Represents an abstract command
 */
public abstract class Command {
    /**
     * Executes the command
     */
    public abstract void execute();

    /**
     * Returns whether this command represents an exit command
     */
    public boolean isExit() {
        return false;
    }
}
