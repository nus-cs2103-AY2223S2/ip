package Duke.Command;

import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

/**
 * Class in charge of handling the case to mark Task
 */
public class MarkTask implements Command {
    private int index;

    public MarkTask(int index) {
        this.index = index;
    }

    /**
     * Marks Task as done, update it to storage, and output result using Ui
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        tl.mark(this.index - 1);
        storage.modify(storage.getStorageTaskString(tl.getTask(this.index - 1)), this.index);
        ui.showMarkResult(tl.getTask(this.index - 1).toString(), this.index);
    }
}
