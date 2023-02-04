package berry.command;

import berry.task.TaskList;
import berry.ui.Ui;
import berry.storage.Storage;
import berry.exception.BerryException;
import berry.exception.IndexOutOfRangeException;

/**
 * Marks the task as done as identified by its last displayed index from the task list.
 */
public class MarkCommand extends Command {

    /** The index of the task to be marked */
    private static int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        String output = "";
        if (tasks.isIndexWithinRange(taskIndex)) {
            output += ui.showMark() + tasks.markDone(taskIndex);
        } else {
            throw new IndexOutOfRangeException();
        }
        storage.saveTasks(tasks);
        return output;
    }
}
