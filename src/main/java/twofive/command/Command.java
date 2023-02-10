package twofive.command;

import twofive.data.TaskList;
import twofive.exception.TwoFiveException;
import twofive.storage.Storage;

/**
 * Results in various actions being performed when commands are executed
 * in initialized subclasses of this class.
 */
public abstract class Command {
    /**
     * Executes actions given a list of tasks, a UI object and a Storage object.
     *
     * @param tasks List of all current tasks.
     * @param storage Storage for saving or loading tasks.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws TwoFiveException;
}
