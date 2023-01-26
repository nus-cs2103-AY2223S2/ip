package duke.command;

import duke.storage.Storage;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddTodoCommand extends Command{
    private String name;
    public AddTodoCommand(String name) {
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
