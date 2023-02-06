package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract command class
 */
public abstract class Command {

    /**
     * Determine if Command is an instance of ExitCommand
     * @return true if Command subclass is ExitCommand
     */
    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
