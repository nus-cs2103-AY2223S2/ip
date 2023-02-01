package Nerd.Commands;

import Nerd.entities.Task;
import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.ListCommand of the Chat bot.
 */
public class ListCommand extends Command {

    /**
     * Overloaded processCommand method from the abstract class Command.
     * Processes the command for a ListCommand.
     *
     * @param list The TaskList object that stores Tasks.
     * @param ui   User interface of the Chat bot.
     */
    public void processCommand(TaskList list, Ui ui) {
        for (int i = 0; i < list.getSize(); i++) {
            Task t = list.getTask(i);
            ui.print(String.format("%d.%s", i + 1, t.toString()));
        }
        ui.printDivider();
    }
}
