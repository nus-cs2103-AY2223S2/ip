package command;

/**
 * Represents an executable command
 */
public abstract class Command {
    public abstract void execute();
    public abstract boolean isExit();
}
