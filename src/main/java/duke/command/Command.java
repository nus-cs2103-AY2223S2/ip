package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles the various commands available in Duke
 */
public abstract class Command {

    /**
     * Executes actions specified by command
     * @throws DukeException if encountering an exception specific to Duke
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /** Specifies Duke should not exit */
    public boolean isExit() {
        return false;
    }
}
