package duke.command;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a delete task command.
 */
public class DeleteCommand extends Command {
    private int toDelete;

    /**
     * Constructs a delete task command.
     *
     * @param toDelete Integer index of task to be deleted from task collection.
     */
    public DeleteCommand(int toDelete) {
        this.toDelete = toDelete;
    }

    @Override
    public boolean isGoodbye() {
        return false;
    }

    /**
     * Deletes tasks from task collection.
     * Returns task has been deleted message.
     *
     * @param tasks Tasklist object.
     * @param storage Storage object.
     * @param ui Ui Object.
     * @return Task has been deleted message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        return tasks.deleteTask(toDelete);
    }
}
