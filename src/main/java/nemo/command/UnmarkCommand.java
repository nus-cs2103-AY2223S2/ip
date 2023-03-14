package nemo.command;

import nemo.exception.NemoException;
import nemo.storage.Storage;
import nemo.task.Task;
import nemo.task.TaskList;
import nemo.ui.Ui;

/**
 * Command to mark Task as undone.
 *
 * @author Lian Kok Hai
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs new UnmarkCommand.
     *
     * @param index 1-based index of Task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws NemoException {
        Task unmarkedTask = taskList.unmarkTask(index);
        storage.saveTaskList(taskList);
        return ui.getUnmarkTaskMessage(unmarkedTask);
    }
}
