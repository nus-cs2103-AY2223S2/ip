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
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;

    /**
     * Returns whether the command requires the program to exit.
     *
     * @return True if the program should exit, false otherwise.
     */
    public abstract boolean isExit();
}
