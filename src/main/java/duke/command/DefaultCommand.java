package duke.command;

import duke.exceptions.TaskException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Gives command to execute default action
 */
public class DefaultCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws TaskException {
        ui.error("default");
        return "Try again";
    }
}
