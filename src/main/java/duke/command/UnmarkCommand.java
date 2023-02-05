package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Unmarks task that is done
 */
public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(String value) {
        this.index = Integer.valueOf(value);
    }

    /**
     * Executes the command given by the user.
     *
     * @param tasks   to be modified
     * @param ui      to display changes
     * @param storage to interact with as necessary
     * @return Response string.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("Unable to unmark.");
        }
        Task curr = tasks.get(index - 1);
        return ui.showUnmarked(curr);
    }
}
