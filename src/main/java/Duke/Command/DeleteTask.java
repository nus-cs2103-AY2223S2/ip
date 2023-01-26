package Duke.Command;

import Duke.Storage.Storage;
import Duke.Task.Task;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

/**
 * Class to handle the case of deleting Task
 */
public class DeleteTask implements Command {
    private int index;
    public DeleteTask(int index) {
        this.index = index;
    }

    /**
     * Delete task from TaskList and Storage, and output result using Ui
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        storage.delete(index);
        Task t = tl.getTask(index - 1);
        tl.remove(index - 1);
        ui.showDeleteResult(t.toString(), tl.getSize());
    }
}
