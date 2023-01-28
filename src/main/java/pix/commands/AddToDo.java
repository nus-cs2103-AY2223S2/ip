package duke.commands;

import duke.data.MyData;
import duke.tasks.ToDo;
import duke.ui.Ui;

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

    public void execute(MyData data, Ui ui) {
        data.setData(todo);
        data.saveToFile();
        ui.add(todo.toString(), data.len());
    }
}

