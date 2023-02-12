package kuromi.command;

import kuromi.Storage;
import kuromi.Ui;
import kuromi.task.Task;
import kuromi.task.TaskList;

/**
 * Mark task as not done.
 */
public class UnmarkCommand extends Command {
    /** Index of the task to be unmarked. **/
    private int idx;

    /**
     * kuromi.MainWindow.kuromi.KuromiException.Main constructor.
     *
     * @param idx Index of the task to be unmarked.
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Mark the task as not done.
     * Ask UI to print the ouput.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task cur = tasks.unmark(this.idx - 1);
        return ui.show(getReply(cur));
    }

    private String getReply(Task cur) {
        String msg = "OK, I've marked this task as not done yet:\n";
        msg += cur + "\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\nNote:\n";
        msg += "Please do the task soon -_-";
        return msg;
    }
}
