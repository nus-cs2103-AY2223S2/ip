package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulation of the command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;
    /**
     * Constructor for DeleteCommand.
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }
    /**
     * Deletes the task at the given index as done.
     * @throws DukeException if given index is out of range.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.delete(this.index);
    }

    /**
     * Returns whether the program should exit or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}