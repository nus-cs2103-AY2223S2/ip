package Nerd.Commands;

import Nerd.entities.Task;
import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.MarkCommand of the Chat bot.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructor of mark commands
     *
     * @param index The index of the task.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Overridden processCommand method from the abstract class Command.
     * Processes the command for a MarkCommand.
     *
     * @param list The TaskList object that stores Tasks.
     * @param ui   User interface of the Chat bot.
     * @return The task being marked.
     */
    @Override
    public String processCommand(TaskList list, Ui ui) {
        Task task = list.getTask(index);
        task.setDone();
        String output = ui.printMarkResponse(task.toString());
        return output;
    }
}
