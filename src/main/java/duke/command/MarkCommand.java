package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior of the command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;
    /**
     * Instantiates MarkCommand.
     *
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
        tasks.mark(this.index);
    }

    /**
     * Returns whether the command requires the program to exit.
     *
     * @return False indicating that program should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
