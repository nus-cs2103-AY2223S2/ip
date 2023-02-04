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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        String output = "";
        if (tasks.isIndexWithinRange(taskIndex)) {
            output += ui.showDelete();
            tasks.deleteTask(taskIndex);
        } else {
            throw new IndexOutOfRangeException();
        }
        storage.saveTasks(tasks);
        return output;
    }
}
