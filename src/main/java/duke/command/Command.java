package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** Class that represents a user command */
public abstract class Command {
    /** Whether Duke should exit */
    private boolean isExit;

    /**
     * Constructs a Command object.
     *
     * @param isExit Whether this command
     *               exits Duke.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Execute this command.
     *
     * @param taskStore Current task list.
     * @param ui User interface.
     * @param storage Storage.
     * @throws DukeException if execution fails.
     */
    public abstract void execute(TaskList taskStore, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }

}
