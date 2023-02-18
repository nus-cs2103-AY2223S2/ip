package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class in charge of handling the case of Unmakring Task
 */
public class UnmarkTaskCommand extends Command {
    private int index;

    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Marks Task as undone, update it to storage, and return result string
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     * @return output string
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        tl.unmark(this.index - 1);
        storage.modify(storage.getStorageTaskString(tl.getTask(this.index - 1)), this.index);
        return ui.showUnmarkResult(tl.getTask(this.index - 1).toString(), this.index);
    }
}
