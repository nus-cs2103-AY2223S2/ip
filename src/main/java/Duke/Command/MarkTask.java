package Duke.Command;

import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

public class MarkTask implements Command {
    private int index;

    public MarkTask(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        tl.mark(this.index - 1);
        storage.modify(storage.getStorageTaskString(tl.getTask(this.index - 1)), this.index);
        ui.showMarkResult(tl.getTask(this.index - 1).toString(), this.index);
    }
}
