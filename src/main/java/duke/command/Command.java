package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract parent class for the other commands
 */
public abstract class Command {
    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param ui      object to reply to user after the command has executed
     * @param storage object required when command writes to file
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract String executeString(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public abstract boolean isExit();
}
