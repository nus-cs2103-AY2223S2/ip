package reborn.command;

import reborn.data.TaskList;
import reborn.data.exception.RebornException;
import reborn.data.task.Task;
import reborn.storage.Storage;
import reborn.ui.Ui;

/**
 * Marks task as done.
 */
public class MarkCommand extends Command {
    private int index;
    public MarkCommand(String value) {
        this.index = Integer.valueOf(value);
    }

    /**
     * Executes the command given by the user.
     *
     * @param tasks   to be modified
     * @param ui      to display changes
     * @param storage to interact with as necessary
     * @return Response string.
     * @throws RebornException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RebornException {
        if (tasks.isEmpty()) {
            throw new RebornException("Unable to mark.");
        }
        Task curr = tasks.get(index - 1);
        return ui.showMarked(curr);
    }
}
