package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Abstract class that encompasses all types of user commands.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param tasks TaskList containing all the currently stored Tasks.
     * @param ui Ui that deals with interactions with the user.
     * @param storage Storage that loads and saves tasks to the file containing currently stored Tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the Command terminates the Duke Program.
     * @return the boolean value on whether to terminate the Duke Program.
     */
    public abstract boolean isExit();
}
