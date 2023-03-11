package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.UI.UI;

/**
 * The superclass command for all command types.
 */
public abstract class Command {

    /**
     * Runs the command.
     * @param task Task List
     * @param ui UI that shows response to the user.
     * @param storage Extracts out the data from the storage folder in the disk.
     */
    public abstract void runCommand(TaskList task, UI ui, Storage storage);

    /**
     * @return true if it is a bye command, false otherwise.
     */
    public abstract boolean isExit();
}
