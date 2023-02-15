package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that is used to delete a task when executed.
 */
public class DeleteCommand extends Command {
    private int deleteIdx;

    /**
     * Class constructor of DeleteCommand.
     * @param deleteIdx the index of the task to be deleted
     */
    public DeleteCommand(int deleteIdx) {
        this.deleteIdx = deleteIdx;
    }

    /**
     * Deletes the task with an index equal to deleteIdx from the TaskList.
     * @param tasks the TaskList of the Duke
     * @param ui the Ui of the Duke
     * @param storage the storage of the Duke
     * @return the message to indicate deletion of the task
     * @throws DukeException if error occurs during deletion of the task
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String res = tasks.delete(deleteIdx);
        return res;
    }

    /**
     * Returns true when the command indicates the closure of the software.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
