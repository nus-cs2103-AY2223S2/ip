package kuromi.command;

import kuromi.storage.Storage;
import kuromi.view.Ui;
import kuromi.task.Task;
import kuromi.task.TaskList;

/**
 * Command to add a task to the TaskList.
 */
public class AddCommand extends Command {
    /** The task to be added **/
    private Task task;

    /**
     * kuromi.gui.component.MainWindow.kuromi.exceptions.KuromiException.Main constructor
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
        int prevNumTasks = tasks.size();
        Task cur = tasks.add(task);
        assert(prevNumTasks + 1 == tasks.size()) : "Tasks size should be equal to previous tasks size + 1";
        assert(tasks.size() > 0) : "Tasks size should be greater than 0 after AddCommand";
        storage.refresh(tasks);
        return ui.show(getReply(cur, tasks));
    }

    private String getReply(Task cur, TaskList tasks) {
        String msg = "Got it. I've added this task:\n";
        msg += cur + "\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\nNote:\n";
        msg += "Now you have " + tasks.size() + " tasks in the list.";
        return msg;
    }
}
