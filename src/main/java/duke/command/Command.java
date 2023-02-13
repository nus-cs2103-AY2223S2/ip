package duke.command;

import duke.exception.DukeEventOverlapException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeIoException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represent an executable command.
 */
public abstract class Command {

    /**
     * Return true if it is exit command.
     *
     * @return true if it is exit command.
     */
    protected abstract boolean isExit();

    /**
     * Execute the program given a specific command.
     *
     * @param task list that store the task.
     * @param ui User Interface of the application.
     * @param storage Database that store previous undelete task.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     * @throws DukeIoException indicate failed or interrupted I/O operations occurred.
     */
    public abstract String execute(TaskList task, Ui ui, Storage storage) throws DukeInvalidArgumentException,
            DukeIoException, DukeEventOverlapException;
}
