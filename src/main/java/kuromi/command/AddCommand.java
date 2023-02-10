package kuromi.command;

import kuromi.Storage;
import kuromi.Ui;
import kuromi.task.Task;
import kuromi.task.TaskList;

/**
 * Command to add a task to the TaskList.
 */
public class AddCommand extends Command {
    /** The task to be added **/
    private Task task;

    /**
     * kuromi.MainWindow.kuromi.KuromiException.Main constructor
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task cur = tasks.add(task);
        storage.refresh(tasks);
        return ui.show(getReply(cur, tasks));
    }

    private String getReply(Task cur, TaskList tasks) {
        String msg = "Got it. I've added this task:\n";
        msg += cur + "\n";
        msg += "Now you have " + tasks.size() + " tasks in the list.\n";
        return msg;
    }
}
