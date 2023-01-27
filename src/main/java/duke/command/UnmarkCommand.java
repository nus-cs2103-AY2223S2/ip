package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * Encapsulation of the command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;
    /**
     * Constructor for UnmarkCommand.
     * @param index The index of the task to be marked.
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
     * @throws DukeException if given index is out of range.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.unmark(this.index);
    }

    /**
     * Returns whether the program should exit or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}