package pix.commands;

import pix.data.MyData;
import pix.tasks.ToDo;
import pix.ui.Ui;

/**
 * ToDoCommand class to add to do to list of tasks.
 */
public class ToDoCommand extends Command {
    /** To do object to be added. */
    protected ToDo todo;

    /**
     * Constructs a new ToDoCommand.
     *
     * @param description Description of the task to do.
     */
    public ToDoCommand(String description) {
        this.todo = new ToDo(description);
    }

    /**
     * Executes the ToDoCommand and displays result.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public String execute(MyData data, Ui ui) {
        data.addTask(todo);
        data.saveToFile();
        return ui.add(todo.toString(), data.len());
    }
}

