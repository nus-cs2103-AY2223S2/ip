package Nerd.Commands;

import Nerd.entities.Task;
import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.DeleteCommand of the Chat bot.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor of delete commands.
     *
     * @param index The index of the command to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }


    /**
     * Overridden processCommand method from the abstract class Command.
     * Processes the command for a DeleteCommand Task.
     *
     * @param list The TaskList object that stores Tasks.
     * @param ui   User interface of the Chat bot.
     * @return The string output of processing a delete command.
     */
    @Override
    public String processCommand(TaskList list, Ui ui) {
        Task task = list.getTask(index);
        list.deleteTask(index);
        String response = ui.printDeleteResponse(task.toString(), list.getSize());
        return response;
    }
}
