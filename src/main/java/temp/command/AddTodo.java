package duke.command;

import duke.storage.Storage;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddTodo implements Command{
    private String name;
    public AddTodo(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        Todo td = new Todo(this.name);
        tl.addTask(td);
        storage.add(storage.getStorageTaskString(td));
        ui.showAddTodoResult(td.toString(), tl.getSize());
    }
}
