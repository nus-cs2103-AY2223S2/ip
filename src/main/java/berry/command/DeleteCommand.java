package berry.command;

import berry.task.TaskList;
import berry.ui.Ui;
import berry.storage.Storage;
import berry.exception.BerryException;
import berry.exception.IndexOutOfRangeException;

/**
 * Deletes a task identified by its last displayed index from the task list.
 */
public class DeleteCommand extends Command {

    /** The index of the task to be deleted */
    private static int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        if (tasks.isIndexWithinRange(taskIndex)) {
            ui.showDelete();
            tasks.deleteTask(taskIndex);
        } else {
            throw new IndexOutOfRangeException();
        }
    }
}
