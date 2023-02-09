package command;

public abstract class Command {
    /**
     * Executes the various commands
     */
    public abstract void execute();

    /**
     * Checks if the current command is an ExitCommand
     * @return true if current command is an ExitCommand, and false otherwise
     */
    public abstract boolean isExit();
}
