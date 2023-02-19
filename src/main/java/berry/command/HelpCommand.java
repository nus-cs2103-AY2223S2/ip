package berry.command;

import berry.exception.BerryException;
import berry.storage.Storage;
import berry.task.TaskList;
import berry.ui.Ui;

/**
 * Shows the help menu
 */
public class HelpCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        return ui.showHelpMenu();
    }
}
