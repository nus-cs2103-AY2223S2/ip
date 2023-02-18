package reborn.command;

import reborn.data.TaskList;
import reborn.storage.Storage;
import reborn.ui.Ui;

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
     * @return Response string.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showAllTasks(tasks);
    }
}
