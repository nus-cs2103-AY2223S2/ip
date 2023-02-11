package duke.command;

import duke.exception.DukeException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Executes a command based on parsed inputs.
 */
public abstract class Command {
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
     * Returns false by default and true if "bye" command is executed
     *
     * @return false by default and true if "bye" command is executed
     */
    public boolean isExit() {
        return false;
    }
}
