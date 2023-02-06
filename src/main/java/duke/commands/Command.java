package duke.commands;

import duke.Storage;
import duke.TaskException;
import duke.TaskList;

/**
 * Represents an abstract command
 */
public abstract class Command {
    /**
     * Executes the command
     */
    public abstract String execute(TaskList tasks, Storage storage) throws TaskException;

    /**
     * Returns whether this command represents an exit command
     */
    public boolean isExit() {
        return false;
    }
}
