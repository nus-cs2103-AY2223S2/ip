package Duke.Command;

import Duke.Storage.Storage;
import Duke.Task.Todo;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

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
