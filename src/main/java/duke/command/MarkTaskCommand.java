package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class in charge of handling the case to mark Task
 */
public class MarkTaskCommand extends Command {
    private int index;

    public MarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Marks Task as done, update it to storage, and output result using Ui
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     * @return output string
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        tl.mark(this.index - 1);
        storage.modify(storage.getStorageTaskString(tl.getTask(this.index - 1)), this.index);
        return ui.showMarkResult(tl.getTask(this.index - 1).toString(), this.index);
    }
}
