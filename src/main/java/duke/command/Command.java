package duke.command;

import duke.ui.Ui;

/**
 * Represents a command that is entered by the user
 */
public abstract class Command {
    protected Ui ui;


    public Command(Ui ui) {
        this.ui = ui;
    }

    /**
     * Runs the given command.
     */
    public abstract void runCommand();





}
