package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that is used to mark a task as undone when executed.
 */
public class UnmarkCommand extends Command {
    private int unmarkIdx;

    /**
     * Class constructor of MarkCommand.
     * @param unmarkIdx the index of the task to be marked as undone
     */
    public UnmarkCommand(int unmarkIdx) {
        this.unmarkIdx = unmarkIdx;
    }

    /**
     * Marks the task with an index equal to unmarkIdx as undone.
     * @param tasks the TaskList of the Duke
     * @param ui the Ui of the Duke
     * @param storage the storage of the Duke
     * @return the message that indicates that the task was marked as undone
     * @throws DukeException if the given unmarkIdx is larger than the number of tasks in the TaskList
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.unmarkIdx >= tasks.size()) {
            throw new DukeException(
                "The task with the given index does not exist.");
        }
        String res = tasks.getTask(this.unmarkIdx).unmark();
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
