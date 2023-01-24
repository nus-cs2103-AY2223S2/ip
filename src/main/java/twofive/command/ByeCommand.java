package twofive.command;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.ui.Ui;

import java.io.IOException;

/**
 * Results in the program saving current tasks and exiting when this command
 * is executed.
 */
public class ByeCommand extends Command {
    /**
     * Saves current tasks into storage and displays exit message.
     * Displays error message if error encountered while saving tasks.
     *
     * @param tasks List of all current tasks.
     * @param ui UI interacting with user.
     * @param storage Storage for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
            ui.showExit();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
