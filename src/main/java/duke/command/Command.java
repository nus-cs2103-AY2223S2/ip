package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Handles the commands available in Duke
 */
public abstract class Command {

    /**
     * Executes command.
     * Returns response after executing command.
     *
     * @param tasks List of tasks.
     * @param ui Handles user interaction.
     * @param storage Handles saving and loading tasks.
     * @return Response from executing the command.
     * @throws DukeException if encountering an exception specific to Duke.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /** Checks if Duke should exit */
    public boolean isExit() {
        return false;
    }
}
