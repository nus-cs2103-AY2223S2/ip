package kuromi.command;

import kuromi.Storage;
import kuromi.Ui;
import kuromi.task.TaskList;

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
        String msg = "Here are the upcoming " + numberOfTasks + " tasks:\n";
        msg += tasks.getUpcomingTasks(numberOfTasks).toString() + "\n";
        return msg;
    }
}
