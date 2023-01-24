package duke.command;

import duke.ui.Ui;
/**
 * Represents a "help" command that is entered by the user.
 */
public class HelpCommand extends Command {
    /**
     * Constructs a HelpCommand.
     */
    public HelpCommand(Ui ui) {
        super(ui);
    }

    /**
     * Causes the bot to print out the list of supported commands.
     */
    @Override
    public void runCommand() {
        ui.printCommands();
    }

}
