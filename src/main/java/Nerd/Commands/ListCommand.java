package Nerd.Commands;

import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.ListCommand of the Chat bot.
 */
public class ListCommand extends Command {

    /**
     * Overridden processCommand method from the abstract class Command.
     * Processes the command for a ListCommand.
     *
     * @param list The TaskList object that stores Tasks.
     * @param ui   User interface of the Chat bot.
     * @return The string of all task currently in the TaskList.
     */
    @Override
    public String processCommand(TaskList list, Ui ui) {
        return ui.printListResponse(list);
    }
}
