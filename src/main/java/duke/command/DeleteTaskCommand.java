package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class to handle the case of deleting Task
 */
public class DeleteTaskCommand extends Command {
    private int index;
    public DeleteTaskCommand(int index) {
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
