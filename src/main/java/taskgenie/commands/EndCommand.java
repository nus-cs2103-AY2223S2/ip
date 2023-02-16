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
     * @return String for executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        storage.store(tasks);
        return ui.showFarewell();
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
