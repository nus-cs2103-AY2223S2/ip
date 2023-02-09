package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior of the command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Instantiates DeleteCommand object.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the given index.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @return A string message to signify the success or failure of task executed.
     * @throws DukeException if given index is out of range.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.delete(this.index);
    }
}
