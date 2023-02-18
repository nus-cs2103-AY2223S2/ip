package kuromi.command;

import kuromi.storage.Storage;
import kuromi.task.TaskList;
import kuromi.view.Ui;

/**
 * Command to list or remind the user the first n tasks in the task list.
 */
public class RemindCommand extends Command {
    private int numberOfTasks;

    public RemindCommand(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.show(getReply(tasks, numberOfTasks));
    }

    private String getReply(TaskList tasks, int numberOfTasks) {
        if (tasks.size() == 0) {
            String msg = "You have no upcoming tasks :(";
            return msg;
        }
        String msg = "Here are your upcoming " + numberOfTasks + " tasks:\n";
        msg += tasks.getUpcomingTasks(numberOfTasks).toString() + "\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\nNote:\n";
        msg += "I know you won't finish the tasks on time :D";
        return msg;
    }
}
