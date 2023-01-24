package duke.command;

import duke.ui.Ui;
/**
 * Represents a "bye" command that is entered by the user.
 */
public class ByeCommand extends Command {


    /**
     * Constructs a ByeCommand.
     */
    public ByeCommand(Ui ui) {
        super(ui);
    }

    /**
     * Causes the bot to print exit message, and properly exit.
     */
    @Override
    public void runCommand() {
        ui.printExitMessage();
    }

}
