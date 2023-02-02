package duke.command;

import duke.Ui;

public abstract class Command {
    /**
     * Handles the execution of each command.
     * @param ui
     */
    public abstract void handleCommand(Ui ui);

    /**
     * Gives an indicator whether it is the exit command.
     * @return
     */
    public abstract boolean isExit();
}
