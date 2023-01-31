package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * An abstraction over the possible commands that can be handled by Clippy.
 *
 * @author chunzkok
 */
public abstract class Command {

    /**
     * Returns true if the program should continue. Otherwise, it should terminate.
     *
     * @return True by default, false only if program should terminate
     */
    public boolean shouldContinue() {
        return true;
    }

    /**
     * Executes the command handler for the specific Command.
     *
     * @param ui The Ui instance of the current run.
     * @param taskList The TaskList instance of the current run.
     * @param storage The Storage instance of the current run.
     */
    public abstract void execute(Ui ui, TaskList taskList, Storage storage);
}
