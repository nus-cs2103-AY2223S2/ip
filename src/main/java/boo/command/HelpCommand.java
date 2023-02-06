package boo.command;

import boo.ui.Ui;

/**
 * Represents a "help" command that is entered by the user.
 */
public class HelpCommand extends Command {
    /**
     * Constructs a {@code HelpCommand}.
     */
    public HelpCommand() {
        super();
    }

    /**
     * Causes the bot to print out the list of supported commands.
     *
     * @return the list of supported commands
     */
    @Override
    public String runCommand() {
        String output = "";
        output += "Supported Commands:\n";
        output += Ui.COMMAND_LIST;
        return output;
    }
}
