package nemo.command;

import nemo.exception.NemoException;
import nemo.storage.Storage;
import nemo.task.Task;
import nemo.task.TaskList;
import nemo.ui.Ui;

/**
 * Command to mark Task as done.
 *
 * @author Lian Kok Hai
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs new MarkCommand.
     *
     * @param index 1-based index of Task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws NemoException {
        Task markedTask = taskList.markTask(index);
        storage.saveTaskList(taskList);
        return ui.getMarkTaskMessage(markedTask);
    }
}
