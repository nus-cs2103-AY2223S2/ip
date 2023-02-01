package kuromi.command;

import kuromi.Storage;
import kuromi.Ui;
import kuromi.task.Task;
import kuromi.task.TaskList;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /** Index of the task to be deleted in the task list. **/
    private int idx;

    /**
     * Main constructor
     *
     * @param idx Index of the task to be deleted.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Delete task from the task list.
     * Ask UI to print the output.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task cur = tasks.delete(this.idx - 1);
        String msg = "Noted. I've removed this task:\n";
        msg += cur + "\n";
        msg += "Now you have " + tasks.size() + " tasks in the list.";
        return ui.show(msg);
    }
}
