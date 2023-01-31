package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Command handler for the `bye` command.
 *
 * @author chunzkok
 */
public class ByeCommand extends Command {

    /**
     * Saves program state and exits.
     *
     * @param ui The Ui instance of the current run.
     * @param taskList The TaskList instance of the current run.
     * @param storage The Storage instance of the current run.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.systemPrint("Saving state, please wait...");
        storage.saveState(taskList.getList());
        ui.systemPrint("State successfully saved.");
        ui.prettyPrint("Hope I helped. Goodbye!");
    }

    /**
     * Returns false to indicate that the program should terminate.
     *
     * @return false
     */
    @Override
    public boolean shouldContinue() {
        return false;
    }
}
