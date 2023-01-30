package duke.command;


import duke.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

public class AddTaskCommand extends Command {

    protected Task task;

    /**
     * Initialises the object
     *
     * @param task The task object to be contained
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the current command
     *
     * @param list The task list
     * @param ui The ui object
     * @param storage The storage object
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        list.add(task);
        ui.addTask(task, list.getLength());
        storage.write(list);
    }

    /**
     * Checks if this is exit command
     */
    public boolean isExit() {
        return false;
    }

}
