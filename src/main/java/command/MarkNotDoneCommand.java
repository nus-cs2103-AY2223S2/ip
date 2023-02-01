package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

/**
 * Marks a task as not done.
 */
public class MarkNotDoneCommand extends Command {

    private final int index;

    /**
     * Constructs MarkNotDoneCommand.
     *
     * @param index Index of task to be marked as not done.
     */
    public MarkNotDoneCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Marks a task as not done by index, outputs message to user and updates the file.
     *
     * @param taskList List of task.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException Throws exception if index is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index > taskList.getTotalNumberOfTask()) {
            throw new DukeException("Index too large");
        } else if (index <= 0) {
            throw new DukeException("Index too small");
        }
        Task t = taskList.taskNotDone(index);
        storage.writeFile(taskList);
        ui.outputTaskNotDone(t);
    }
}

