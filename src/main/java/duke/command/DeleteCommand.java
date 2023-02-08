package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The type Delete command.
 */
public class DeleteCommand extends Command {

    private int indexOfTaskToDelete;

    /**
     * Instantiates a new Delete command.
     *
     * @param indexOfTaskToDelete the index of task to delete
     */
    public DeleteCommand(int indexOfTaskToDelete) {
        this.indexOfTaskToDelete = indexOfTaskToDelete;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (indexOfTaskToDelete >= tasks.size() || indexOfTaskToDelete < 0) {
            throw new DukeException("invalid task number provided");
        }
        return tasks.delete(indexOfTaskToDelete);
    }
}
