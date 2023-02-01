package Nerd.Commands;

import Nerd.entities.TaskList;
import Nerd.entities.Todo;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.TodoCommand of the Chat bot.
 */
public class TodoCommand extends Command {

    /**
     * Overloaded processCommand method from the abstract class Command.
     * Processes the command for a TodoCommand.
     *
     * @param list The TaskList object that stores Tasks.
     * @param desc The description of the Task.
     * @param ui   User interface of the Chat bot.
     */
    public void processCommand(TaskList list, String desc, Ui ui) {
        Todo task = new Todo(desc);
        list.addTask(task);
        ui.print(String.format("alright, I've added the following task:\n %s", task.toString()));
        ui.print(String.format("Now you have %d tasks in the list.", list.getSize()));
        ui.printDivider();
    }

}
