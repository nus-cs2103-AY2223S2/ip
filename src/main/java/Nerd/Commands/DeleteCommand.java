package Nerd.Commands;

import Nerd.entities.Task;
import Nerd.entities.TaskList;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.DeleteCommand of the Chat bot.
 */
public class DeleteCommand extends Command {

    /**
     * Overloaded processCommand method from the abstract class Command.
     * Processes the command for a DeleteCommand Task.
     *
     * @param list  The TaskList object that stores Tasks.
     * @param index The position of the Task in the TaskList to be deleted.
     * @param ui    User interface of the Chat bot.
     */

    public String processCommand(TaskList list, int index, Ui ui) {
        Task t = list.getTask(index);
        list.deleteTask(index);
        return String.format("ok, this task has been removed:\n %s\nNow you have %d tasks in the list",
                t.toString(), list.getSize());
    }
}
