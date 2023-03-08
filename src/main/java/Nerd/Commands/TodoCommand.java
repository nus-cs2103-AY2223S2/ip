package Nerd.Commands;

import Nerd.entities.TaskList;
import Nerd.entities.Todo;
import Nerd.Ui.Ui;

/**
 * Represents the Duke.Commands.TodoCommand of the Chat bot.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructor of Todo commands
     *
     * @param description The description of the task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Overridden processCommand method from the abstract class Command.
     * Processes the command for a TodoCommand.
     *
     * @param list The TaskList object that stores Tasks.
     * @param ui   User interface of the Chat bot.
     * @return The string result of adding a todo task.
     */
    @Override
    public String processCommand(TaskList list, Ui ui) {
        Todo task = new Todo(description);
        list.addTask(task);
        String output = ui.printTodoResponse(task.toString(), list.getSize());
        return output;
    }

}
