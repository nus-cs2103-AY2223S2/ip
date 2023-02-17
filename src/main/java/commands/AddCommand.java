package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
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
     * Adds the specified Task to the TaskList, informs the Ui to display the added task,
     * and saves the updated TaskList to the file using the Storage object.
     *
     * @param list the TaskList to add the Task to
     * @param ui the Ui to help inform the user of the addition
     * @param storage the Storage to save the updated TaskList to
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.printOutput("Got it. I've added this task:\n\t\t" + task.toString() + "\n\t Now you have "
                + (list.getSize() + 1) + " tasks in the list.");
        list.addTask(task);
        storage.saveListToFile(list, ui);
    }
}
