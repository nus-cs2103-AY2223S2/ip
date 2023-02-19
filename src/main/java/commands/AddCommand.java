package commands;

import nook.Storage;
import nook.TaskList;
import nook.Ui;
import tasks.Task;

/**
 * Represents the command that adds a task to the tasklist.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs a new AddCommand with the specified CommandType and Task.
     *
     * @param type the type of this Command
     * @param task the Task to be added
     */
    public AddCommand(CommandType type, Task task) {
        super(type);
        this.task = task;
    }

    /**
     * Executes this AddCommand with a specified TaskList, Ui, and Storage.
     * Adds the specified Task to the TaskLis, saves the updated TaskList to
     * the file using the Storage object, informs the Ui to display the added task
     *
     * @param list the TaskList to add the Task to
     * @param ui the Ui to help inform the user of the addition
     * @param storage the Storage to save the updated TaskList to
     *
     * @return The execution result string.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        list.addTask(task);
        storage.saveListToFile(list, ui);
        return "Got it. I've added this task:\n" + task.toString() + "\n Now you have "
                + list.getSize() + " tasks in the list.";
    }
}
