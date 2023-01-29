package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Lists all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command given by the user.
     *
     * @param tasks   to be modified
     * @param ui      to display changes
     * @param storage to interact with as necessary
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showAllTasks(tasks);
    }
}
