package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to mark Task as undone.
 *
 * @author Lian Kok Hai
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs new UnmarkCommand.
     *
     * @param index 1-based index of Task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = taskList.unmarkTask(index);
        storage.saveTaskList(taskList);
        return ui.getUnmarkTaskMessage(unmarkedTask);
    }
}
