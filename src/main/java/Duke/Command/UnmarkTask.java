package Duke.Command;

import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

/**
 * Class in charge of handling the case of Unmakring Task
 */
public class UnmarkTask implements Command {
    private int index;

    public UnmarkTask(int index) {
        this.index = index;
    }

    /**
     * Marks Task as undone, update it to storage, and output result using Ui
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        tl.unmark(this.index - 1);
        storage.modify(storage.getStorageTaskString(tl.getTask(this.index - 1)), this.index);
        ui.showUnmarkResult(tl.getTask(this.index - 1).toString(), this.index);
    }
}
