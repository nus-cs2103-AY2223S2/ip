package duke.command;

import duke.ui.Ui;

/**
 * Represents a command that is entered by the user
 */
public abstract class Command {
    /** The Ui for the command to print output. */
    protected Ui ui;

    /** Whether the command should cause the bot to exit. */
    protected boolean isExit = false;

    /**
     * Constructs a Command instance.
     *
     * @param ui The Ui for the command to print output.
     */
    public Command(Ui ui) {
        this.ui = ui;
    }

    /**
     * Runs the given command.
     */
    public abstract void runCommand();

    /**
     * Checks if the command should cause the bot to exit.
     *
     * @return true if the command should cause the bot to exit.
     */
    public boolean isExit() {
        return isExit;
    }
}
