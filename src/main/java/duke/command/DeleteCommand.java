package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * Command to delete Task from TaskList.
 *
 * @author Lian Kok Hai
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Constructs new DeleteCommand.
     *
     * @param index 1-based index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.deleteTask(index);
        ui.printDeleteTaskMessage(deletedTask, taskList.getCount());
        storage.saveTaskList(taskList);
    }
}