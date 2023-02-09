package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

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

    public abstract String execute(TaskList tasks, Storage storage);

}
