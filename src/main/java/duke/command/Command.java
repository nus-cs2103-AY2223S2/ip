package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Skeleton class for all Commands.
 */
public class Command {
    protected boolean isExit;

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Do nothing
    }

    public boolean isExit() {
        return this.isExit;
    }
}
