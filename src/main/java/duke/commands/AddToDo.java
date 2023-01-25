package duke.commands;

import duke.data.MyData;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class AddToDo extends Command {
    protected ToDo todo;

    public AddToDo(String description) {
        this.todo = new ToDo(description);
    }

    public void execute(MyData data, Ui ui) {
        data.setData(todo);
        data.saveToFile();
        ui.add(todo.toString(), data.len());
    }
}

