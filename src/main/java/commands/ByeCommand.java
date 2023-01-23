package commands;

import view.Printable;

/**
 * Represents a command that will exit the application. It has <code>isExit</code> set to true.
 */
public class ByeCommand extends Command {
    /**
     * Generates a <code>ByeCommand</code> object.
     *
     * @param ui A Printable object used for UI display.
     */
    public ByeCommand(Printable ui) {
        super(ui);

        this.isExit = true;
    }

    /**
     * Prints out farewell message.
     */
    @Override
    public void execute() {
        this.ui.printlnIndent("Have a good day! Good bye!");
    }
}
