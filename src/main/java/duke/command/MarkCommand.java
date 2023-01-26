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
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.markIsDone(this.index);
    }

    /**
     * Returns whether the program should exit or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
