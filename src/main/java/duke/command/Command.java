package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior of a command.
 */
public abstract class Command {
    /**
     * Executes the command given.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param ui The class that handles interaction with the users.
     * @return A string message to signify the success or failure of task executed.
     * @throws DukeException If task is not executed successfully.
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;

    /**
     * Returns whether the command requires the program to exit.
     *
     * @return True if the program should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    };
}
