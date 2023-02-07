package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that contains the command information taken in from the user which can be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks the TaskList of the Duke
     * @param ui the Ui of the Duke
     * @param storage the storage of the Duke
     * @throws DukeException if error occurs during execution of the command
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true when the command indicates the closure of the software.
     * @return true when the command indicates the closure of the software
     */
    public abstract boolean isExit();
}
