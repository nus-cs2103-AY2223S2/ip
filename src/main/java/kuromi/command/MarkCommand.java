package kuromi.command;

import kuromi.storage.Storage;
import kuromi.view.Ui;
import kuromi.task.Task;
import kuromi.task.TaskList;

/**
 * Mark task as done.
 */
public class MarkCommand extends Command {
    /** Index of the task to be marked **/
    private int idx;

    /**
     * kuromi.gui.component.MainWindow.kuromi.exceptions.KuromiException.Main constructor.
     *
     * @param idx Index of the task to be marked.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Mark the task as done in the task list.
     * Ask UI to print output.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task cur = tasks.mark(this.idx - 1);
        return ui.show(getReply(cur));
    }

    private String getReply(Task cur) {
        String msg = "Nice! I've marked this task as done:\n";
        msg += cur + "\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\nNote:\n";
        msg += "You finally finished the task! :D";
        return msg;
    }
}
