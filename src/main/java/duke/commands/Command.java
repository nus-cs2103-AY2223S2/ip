package duke.commands;

public abstract class Command {
    /**
     * Handles the execution of each command.
     * @param ui
     */
    public abstract String handleCommand();

    /**
     * Gives an indicator whether it is the exit command.
     * @return
     */
    public abstract boolean isExit();
}
