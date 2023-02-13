package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Mark is a subclass of Command. It has a constructor that takes in a String index and an execute
 * method that takes in a TaskList, Ui and Storage. The execute method marks the task at the given
 * index as done and saves the task list to storage
 */
public class Mark extends Command{
    private int index;
    private Task t;

    public Mark(String index) throws DukeException {
        try {
            this.index = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("invalid index");
        }
    }

    /**
     * The function takes in a task list, a user interface, and a storage, and then marks the task at
     * the index as done, saves the task list, and prints the marked task
     * 
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage Storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if(this.index > tasks.size() - 1 || this.index < 0) {
            throw new DukeException("Sorry, you used an invalid index");
        }
        this.t = tasks.getTask(this.index);
        this.t.markAsDone();
        storage.saveTaskList(tasks);
        ui.printMarked(this.t);
    }
}
