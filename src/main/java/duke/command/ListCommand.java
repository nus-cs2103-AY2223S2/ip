package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles the showing the list of tasks
 */
public class ListCommand extends Command {

    /**
     * Shows the details of tasks from list of tasks
     *
     * @param tasks List of tasks
     * @param ui Handles user interaction
     * @param storage Handles saving and loading tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showTasks(tasks);
    }
}
