package hachi.commands;

import hachi.main.HachiExceptions;
import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.tasks.Todo;


/**
 * Encapsulates a user instruction to add a task to the to-do list.
 */
public class TodoCommand extends Command {
    private String input;

    /**
     * TodoCommand constructor.
     *
     * @param input The user's input string.
     */
    public TodoCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.length() <= 5) {
                throw new HachiExceptions(ui.emptyDescription());
            }
            Todo tdTask = new Todo(input.substring(5, input.length()));
            tasks.add(tdTask);
            return ui.showAdded(tasks, tdTask);
        } catch (HachiExceptions e) {
            return e.getMessage();
        }
    }
}
