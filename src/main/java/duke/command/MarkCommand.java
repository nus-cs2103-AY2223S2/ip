package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that is used to mark a task as done when executed.
 */
public class MarkCommand extends Command {
    private int markIdx;

    /**
     * Class constructor of MarkCommand.
     * @param markIdx the index of the task to be marked as done
     */
    public MarkCommand(int markIdx) {
        this.markIdx = markIdx;
    }

    /**
     * Marks the task with an index equal to markIdx as done.
     * @param tasks the TaskList of the Duke
     * @param ui the Ui of the Duke
     * @param storage the storage of the Duke
     * @return the message that indicates that the task was marked as done
     * @throws DukeException if the given markIdx is larger than the number of tasks in the TaskList
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.markIdx >= tasks.size()) {
            throw new DukeException(
                "The task with the given index does not exist.");
        }
        String res = tasks.getTask(this.markIdx).markDone();
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
