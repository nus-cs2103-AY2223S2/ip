package commands;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * This class is used to end the conversation.
 */
public class EndCommand extends Command {
    /**
     * End the conversation and store the information back into the database.
     * @param tasks The database.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
        storage.store(tasks);
    }

    /**
     * Check to continue the conversation.
     * @return False.
     */
    @Override
    public boolean isContinueConvo() {
        return false;
    }
}
