package Duke.Command;

import Duke.Storage.Storage;
import Duke.Task.Todo;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

/**
 * Class in charge of handling the case of adding a Todotask
 */
public class AddTodo implements Command{
    private String name;
    public AddTodo(String name) {
        this.name = name;
    }

    /**
     * Adds the Task to TaskList and storage, and output result using Ui
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        Todo td = new Todo(this.name);
        tl.addTask(td);
        storage.add(storage.getStorageTaskString(td));
        ui.showAddTodoResult(td.toString(), tl.getSize());
    }
}
