package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior of the command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Instantiates UnmarkCommand.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param ui The class that handles interaction with the users.
     * @return A string message to signify the success or failure of task executed.
     * @throws DukeException if given index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        return tasks.unmarkIsDone(this.index);
    }
}
