package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

/**
 * The type Delete command.
 */
public class DeleteCommand extends Command{

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showAction(tasks.delete(indexOfTaskToDelete));
    }
}
