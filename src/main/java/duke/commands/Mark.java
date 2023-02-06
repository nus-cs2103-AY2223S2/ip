package duke.commands;
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


    public Mark(String index) {
        this.index = Integer.valueOf(index) - 1;
    }

    /**
     * The function takes in a task list, a user interface, and a storage, and then marks the task at
     * the index as done, saves the task list, and prints the marked task
     * 
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage Storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.t = tasks.getTask(this.index);
        this.t.markAsDone();
        storage.saveTaskList(tasks);
        ui.printMarked(this.t);
    }
}
