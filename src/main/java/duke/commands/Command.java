package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command and its arguments.
 */
public abstract class Command {
    /**
     * Executes command.
     *
     * @param tasks Task list.
     * @param ui Ui that shows response to user.
     * @param storage Underlying storage in the disk.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if it's bye command; false otherwise.
     *
     * @return True if it's bye command; false otherwise.
     */
    public abstract boolean isExit();
}
