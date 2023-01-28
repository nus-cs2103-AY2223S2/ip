package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * Command to mark Task as done.
 *
 * @author Lian Kok Hai
 */
public class MarkCommand extends Command {
    int index;

    /**
     * Constructs new MarkCommand.
     *
     * @param index 1-based index of Task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task markedTask = taskList.markTask(index);
        ui.printMarkTaskMessage(markedTask);
        storage.saveTaskList(taskList);
    }
}
