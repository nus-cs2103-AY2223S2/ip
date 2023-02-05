package Nerd.Commands;

import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.ExitCommand of the Chat bot.
 */
public class ExitCommand extends Command {

    /**
     * Overridden processCommand method from the abstract class Command.
     * Processes the command for an ExitCommand.
     *
     * @return The string for processing an exit command.
     */
    @Override
    public String processCommand(TaskList list, Ui ui) {
        return "See you next time!";
    }
}
