package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.IoHandler;

/**
 * The command class represents Duke's function based on user input.
 */
public abstract class Command {

    /**
     * Executes the command function.
     *
     * @param tasks The tasklist to conduct the function with.
     * @param ui    The ui to get user input and print out function notifications.
     * @param store The storage to save the tasklist with.
     * @return An output string for duke, if any.
     * @throws DukeException If the function cannot execute properly.
     */
    public abstract String execute(TaskList tasks, IoHandler ui, Storage store) throws DukeException;

    /**
     * Tells Duke to not exit.
     *
     * @return False.
     */
    public boolean canExit() {
        return false;
    }
}
