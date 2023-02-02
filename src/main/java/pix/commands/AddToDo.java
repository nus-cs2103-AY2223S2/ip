package pix.commands;

import pix.data.MyData;
import pix.tasks.ToDo;
import pix.ui.Ui;

/**
 * AddToDo class to add to do to list of tasks.
 */
public class AddToDo extends Command {
    /** To do object to be added. */
    protected ToDo todo;

    /**
     * Constructs a new AddToDo command.
     *
     * @param description Description of the task to do.
     */
    public AddToDo(String description) {
        this.todo = new ToDo(description);
    }

    /**
     * Executes the Add To Do command and displays result.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public String execute(MyData data, Ui ui) {
        data.setData(todo);
        data.saveToFile();
        return ui.add(todo.toString(), data.len());
    }
}

