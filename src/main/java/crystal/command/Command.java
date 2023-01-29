package crystal.command;

import crystal.CrystalException;
import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;

/**
 * Represents the abstract command class for all
 * the other commands.
 *
 */
public abstract class Command {

    /**
     * Abstract method to execute the respective command
     *
     * @param tasks the tasklist.
     * @param ui ui.
     * @param storage storage.
     *
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CrystalException;

    /**
     * Abstract method to determine whether the program should end or not.
     *
     */

    public abstract boolean isExit();
}

