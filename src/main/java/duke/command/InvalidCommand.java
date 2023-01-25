package duke.command;

import duke.ui.Ui;

/**
 * Represents an invalid command that is entered by the user.
 */
public class InvalidCommand extends Command {
    /**
     * Constructs an InvalidCommand
     */
    public InvalidCommand(Ui ui) {
        super(ui);
    }

    /**
     * Causes the bot indicate that it cannot understand this invalid command.
     */
    @Override
    public void runCommand() {
        Ui.printStraightLine();
        ui.printStatement("Sorry. I do not understand this command. Please try again.");
        Ui.printStraightLine();
    }

}
