package command;

import duke.DukeException;
import task.TaskList;

/**
 * Command deals with executing a user command.
 */
public abstract class Command {
    /**
     * Executes the command which might cause changes to the existing task list and ui.
     * @param tasks The existing task list.
     * @throws DukeException If there is an error encountered during the execution of the command.
     */
    public abstract String execute(TaskList tasks) throws DukeException;

    /**
     * Determines if the current command is an exit command.
     * @return True if the current command is an exit command; false if otherwise.
     */
    public abstract boolean isExit();
}
