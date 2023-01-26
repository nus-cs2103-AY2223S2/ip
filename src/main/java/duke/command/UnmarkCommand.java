package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task cur = tasks.unmark(this.idx - 1);
        String msg = "OK, I've marked this task as not done yet:\n";
        msg += cur;
        ui.show(msg);
    }
}
