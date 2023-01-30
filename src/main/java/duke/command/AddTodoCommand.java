package duke.command;

import duke.storage.Storage;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class in charge of handling the case of adding a Todotask
 */
public class AddTodoCommand extends Command {
    private String name;
    public AddTodoCommand(String name) {
        this.name = name;
    }

    /**
     * Adds the Task to TaskList and storage, and output result string
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     * @return output string
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        Todo td = new Todo(this.name);
        tl.addTask(td);
        storage.add(storage.getStorageTaskString(td));
        return ui.showAddTodoResult(td.toString(), tl.getSize());
    }
}
