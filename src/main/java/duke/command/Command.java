package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * @throws DukeException If the function cannot execute properly.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage store) throws DukeException;

    /**
     * Tells Duke to not exit.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
