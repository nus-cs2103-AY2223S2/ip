package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

/**
 * Mark task as done.
 */
public class MarkCommand extends Command {
    /** Index of the task to be marked **/
    int idx;

    /**
     * Main constructor.
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task cur = tasks.mark(this.idx - 1);
        String msg = "Nice! I've marked this task as done:\n";
        msg += cur;
        ui.show(msg);
    }
}
