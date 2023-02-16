package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;


/**
 * The Delete class is a subclass of Command. It has a constructor that takes in a String index and
 * assigns it to the index variable. It has an execute method that takes in a TaskList, Ui and Storage
 * object. It removes the task at the index from the TaskList and prints a message to the user
 */
public class Delete extends Command {
    private int index;
    private Task t;
    /**
     * A constructor that takes in a String index and assigns it to the index variable.
     */
    public Delete(String index) throws DukeException {
        try {
            this.index = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("invalid index");
        }
    }
    /**
     * This function removes a task from the task list
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage object
     * @return
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.index > tasks.size() - 1 || this.index < 0) {
            throw new DukeException("Sorry, you used an invalid index");
        }
        this.t = tasks.getTask(index);
        tasks.removeTask(index);
        storage.saveTaskList(tasks);
        return ui.printRemovedMessage(this.t, tasks.size());
    }
}
