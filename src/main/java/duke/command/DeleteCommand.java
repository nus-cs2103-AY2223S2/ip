package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.deleteTask(index);
        storage.saveTaskList(taskList);
        return ui.getDeleteTaskMessage(deletedTask, taskList.getCount());
    }
}
