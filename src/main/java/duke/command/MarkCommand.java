package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to mark Task as done.
 *
 * @author Lian Kok Hai
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs new MarkCommand.
     *
     * @param index 1-based index of Task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task markedTask = taskList.markTask(index);
        storage.saveTaskList(taskList);
        return ui.getMarkTaskMessage(markedTask);
    }
}
