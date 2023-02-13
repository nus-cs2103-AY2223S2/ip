package nemo.command;

import nemo.exception.NemoException;
import nemo.storage.Storage;
import nemo.task.Task;
import nemo.task.TaskList;
import nemo.ui.Ui;

/**
 * Command to delete Task from TaskList.
 *
 * @author Lian Kok Hai
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs new DeleteCommand.
     *
     * @param index 1-based index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws NemoException {
        Task deletedTask = taskList.deleteTask(index);
        storage.saveTaskList(taskList);
        return ui.getDeleteTaskMessage(deletedTask, taskList.getCount());
    }
}
