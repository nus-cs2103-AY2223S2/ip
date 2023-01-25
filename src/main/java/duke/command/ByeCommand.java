package duke.command;

import duke.ui.Ui;
/**
 * Represents a "bye" command that is entered by the user.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a <code>ByeCommand</code>.
     */
    public ByeCommand(Ui ui) {
        super(ui);
        isExit = true;
    }

    /**
     * Causes the bot to print exit message, and properly exit.
     */
    @Override
    public void runCommand() {
        ui.printExitMessage();
        ui.cleanUpUi();

    }
}
