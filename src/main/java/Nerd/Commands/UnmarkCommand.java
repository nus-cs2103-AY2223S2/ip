package Nerd.Commands;

import Nerd.entities.Task;
import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.UnmarkCommand of the Chat bot.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructor of Unmark commands
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Overridden processCommand method from the abstract class Command.
     * Processes the command for a TodoCommand.
     *
     * @param list The TaskList object that stores Tasks.
     * @param ui   User interface of the Chat bot.
     * @return The string of the task being marked.
     */
    @Override
    public String processCommand(TaskList list, Ui ui) {
        Task task = list.getTask(index);
        task.setUndone();
        String output = ui.printUnmarkResponse(task.toString());
        return output;
    }
}
