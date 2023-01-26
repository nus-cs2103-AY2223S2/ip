package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class UnmarkTaskCommand extends Command {
    private int index;

    public UnmarkTaskCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        tl.unmark(this.index - 1);
        storage.modify(storage.getStorageTaskString(tl.getTask(this.index - 1)), this.index);
        ui.showUnmarkResult(tl.getTask(this.index - 1).toString(), this.index);
    }
}
