package duke.commands;
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
    public Delete(String index) {
        this.index = Integer.valueOf(index) - 1;
    }
    
    /**
     * This function removes a task from the task list
     * 
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage object
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        this.t = tasks.getTask(index);
        tasks.removeTask(index);
        ui.printRemovedMessage(this.t, tasks.size());
    }
}
