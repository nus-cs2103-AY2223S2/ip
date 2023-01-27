package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * Encapsulation of the command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;
    /**
     * Constructor for MarkCommand.
     * @param index The index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }
    /**
     * Marks the task at the given index as done.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param ui The class that handles interaction with the users.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.mark(this.index);
    }

    /**
     * Returns whether the program should exit or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}