package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
import crystal.task.Todo;

/**
 * Represents the todo command when the user enters "todo".
 *
 */
public class TodoCommand extends Command{


    public String task;

    /**
     * Constructor for TodoCommand class.
     *
     * @param task Task description
     *
     */
    public TodoCommand(String task) {
        this.task = task;
    }

    /**
     * Executes the todo command to print the todo message.
     *
     * @param tasks tasklist.
     * @param ui ui.
     * @param storage storage.
     *
     */
    public String execute(TaskList tasks, Ui ui,Storage storage) {
        Todo todo = new Todo(task);
        tasks.add(todo);
        storage.saveFile(tasks);
        return ui.printTodo(tasks, todo);

    }

    /**
     * Sets the exit condition to false to continue the program.
     *
     */
    public boolean isExit() {
        return false;
    }
}
