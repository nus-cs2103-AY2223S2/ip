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

    public String s;

    /**
     * Constructor for TodoCommand class.
     *
     * @param s Task description
     *
     */
    public TodoCommand(String s) {

        this.s = s;
    }

    /**
     * Executes the todo command to print the todo message.
     *
     * @param tasks tasklist.
     * @param ui ui.
     * @param storage storage.
     *
     */
    public void execute(TaskList tasks, Ui ui,Storage storage) {
        Todo t = new Todo(s);
        tasks.add(t);
        ui.printTodo(tasks, t);

    }

    /**
     * Sets the exit condition to false to continue the program.
     *
     */
    public boolean isExit() {

        return false;
    }
}
