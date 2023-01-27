package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

/**
 * Command to add a task to the TaskList.
 */
public class AddCommand extends Command {
    /** The task to be added **/
    Task task;

    /**
     * Main constructor
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Add the task into the task list.
     * Ask the UI to print the output.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task cur = tasks.add(task);
        storage.refresh(tasks);
        String msg = "Got it. I've added this task:\n";
        msg += cur + "\n";
        msg += "Now you have " + tasks.size() +" tasks in the list.";
        ui.show(msg);
    }
}
