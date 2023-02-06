package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class representing a command a user can input to Duke.
 *
 * @author Lian Kok Hai
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Executes the function of command.
     *
     * @param taskList Tasklist object to be manipulated.
     * @param ui Ui object for printing.
     * @param storage Storage object for loading and saving.
     * @return String representing the command executed
     * @throws DukeException Thrown when error occurs in execution of command.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if command is ExitCommand.
     *
     * @return True if command is ExitCommand.
     */
    public boolean isExit() {
        return isExit;
    }

}
