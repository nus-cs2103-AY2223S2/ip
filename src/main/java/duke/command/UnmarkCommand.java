package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an unmark task as incomplete command.
 */
public class UnmarkCommand extends Command {

    private int toUnmark;

    /**
     * Constructs mark task as incomplete command.
     *
     * @param toUnmark Integer to specify task to be marked as incomplete.
     */
    public UnmarkCommand(int toUnmark) {
        this.toUnmark = toUnmark;
    }

    @Override
    public boolean isGoodbye() {
        return false;
    }

    /**
     * Unmarks specified task as incomplete.
     *
     * @param tasks Tasklist object.
     * @param storage Storage object.
     * @param ui Ui Object.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.unmarkTask(toUnmark);
    }
}
