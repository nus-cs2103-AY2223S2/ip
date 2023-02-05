package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Deletes indexed task from TaskList.
 */
public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(String details) {
        this.index = Integer.valueOf(details);
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
            throw new DukeException("Unable to delete.");
        }
        Task curr = tasks.get(index - 1);
        tasks.remove(index - 1);
        String response = ui.showDeleteTask(curr) + "\n";
        response += ui.showTaskCount(tasks.size());
        return response;
    }
}
