package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command encapsulates various commands done by user.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Contructor for Command.
     *
     * @param isExit Exit status of programme.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Exit status of programme.
     *
     * @return Has the user ended his/her session with duke.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes command according to its type.
     *
     * @param task Tasklist containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @throws DukeException if command cannot be recognised.
     */
    public abstract void execute(TaskList task, Storage storage, Ui ui) throws DukeException;
}
