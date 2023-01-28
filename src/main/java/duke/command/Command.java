package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

/**
 * Abstract parent class for the other commands
 */
public abstract class Command {
    /**
     * Executes the command
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public abstract boolean isExit();
}