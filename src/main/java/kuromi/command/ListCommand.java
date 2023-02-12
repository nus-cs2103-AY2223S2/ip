package kuromi.command;

import kuromi.Storage;
import kuromi.Ui;
import kuromi.task.TaskList;

/**
 * Command to list the tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Ask UI to list the tasks.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.show(getReply(tasks));
    }

    private String getReply(TaskList tasks) {
        String msg = "Here are the tasks in your list:\n";
        msg += tasks.toString() + "\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\nNote:\n";
        msg += "Please finish the tasks soon -_-";
        return msg;
    }
}
