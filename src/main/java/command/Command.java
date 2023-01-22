package command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    /**
     * Executes the command which might cause changes to the existing task list and ui.
     * @param tasks The existing task list.
     * @param ui The ui of Duke chat.
     * @throws DukeException If there is an error encountered during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;

    /**
     * Determines if the current command is an exit command.
     * @return True if the current command is an exit command; false if otherwise.
     */
    public abstract boolean isExit();
}
