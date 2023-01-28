package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * Command to mark Task as undone.
 *
 * @author Lian Kok Hai
 */
public class UnmarkCommand extends Command {
    int index;

    /**
     * Constructs new UnmarkCommand.
     *
     * @param index 1-based index of Task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = taskList.unmarkTask(index);
        ui.printUnmarkTaskMessage(unmarkedTask);
        storage.saveTaskList(taskList);
    }
}
