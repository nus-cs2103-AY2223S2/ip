package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deals with exiting Duke.
 */
public class ExitCommand extends Command {

    /**
     * Executes command input by user.
     *
     * @param tasks List of tasks.
     * @param ui Handles user interaction.
     * @param storage Handles saving and loading tasks.
     * @throws DukeException if encountering an exception specific to Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        exit();

        setResponse(ui.getFarewellMessage());
    }
}
