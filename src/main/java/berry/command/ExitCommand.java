package berry.command;

import berry.exception.BerryException;
import berry.storage.Storage;
import berry.task.TaskList;
import berry.ui.Ui;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        storage.saveTasks(tasks);
        setExit(true);
        return ui.showGoodbye();
    }
}
