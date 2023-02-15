package membot.commands;

import membot.Membot;
import membot.view.Printable;

/**
 * Represents a command that will exit the application. It has <code>isExit</code> set to true.
 */
public class ByeCommand extends Command {
    private final Membot membot;
    /**
     * Generates a <code>ByeCommand</code> object.
     *
     * @param ui A Printable object used for UI display.
     */
    protected ByeCommand(Printable ui, Membot membot) {
        super(ui);

        this.isExit = true;
        this.membot = membot;
    }

    /**
     * Prints out farewell message.
     */
    @Override
    public void execute() {
        this.ui.println("Have a good day! Good bye!");
        this.ui.printSeparator();
        this.membot.exit();
    }
}
