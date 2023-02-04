package Nerd.Commands;

import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.ExitCommand of the Chat bot.
 */
public class ExitCommand extends Command {

    /**
     * Overloaded processCommand method from the abstract class Command.
     * Processes the command for an ExitCommand.
     *
     * @param ui User interface of the Chat bot.
     */
    public String processCommand(Ui ui) {
        return "See you next time!";
    }
}
