package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * The Unmark class is a subclass of Command. It has a constructor that takes in a String index and
 * assigns it to the instance variable index. It has an execute method that takes in a TaskList, Ui and
 * Storage object. It gets the task at the index from the TaskList and marks it as not done. It then
 * saves the TaskList to the Storage and prints the task that was unmarked
 */
public class Unmark extends Command{
    private int index;
    private Task t;

    public Unmark(String index) throws DukeException {
        try {
            this.index = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("invalid index");
        }
    }
    
    /**
     * This function is used to mark a task as not done
     *  @param tasks the list of tasks
     * @param ui the user interface
     * @param storage Storage
     * @return
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if(this.index > tasks.size() - 1 || this.index < 0) {
            throw new DukeException("Sorry, you used an invalid index");
        }
        this.t = tasks.getTask(this.index);
        this.t.markAsNotDone();
        storage.saveTaskList(tasks);
        return ui.printUnmarked(this.t);
    }
}