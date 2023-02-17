package kuromi.command;

import kuromi.storage.Storage;
import kuromi.task.TaskList;
import kuromi.view.Ui;

/**
 * Command to list the tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Asks UI to list the tasks.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.show(getReply(tasks));
    }

    private String getReply(TaskList tasks) {
        if (tasks.size() == 0) {
            String msg = "You don't have any task :(";
            return msg;
        }
        String msg = "Here are the tasks in your list:\n";
        msg += tasks.toString() + "\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\nNote:\n";
        msg += "Please finish the tasks soon -_-";
        return msg;
    }
}
