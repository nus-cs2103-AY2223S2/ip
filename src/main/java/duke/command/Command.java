package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Executes a command based on parsed inputs.
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Executes the given command.
     * Throws exception if the argument is not recognized or in a wrong format.
     *
     * @param tasks TaskList object containing the list of tasks
     * @param ui The Ui object to display messages.
     * @param storage The Storage object to save the task after execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Sets exit as true to exit the session.
     */
    public void setExit() {
        this.isExit = true;
    }
}
