package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;


/**
 * Marks a task as done.
 */
public class MarkDoneCommand extends Command {
    private final int index;

    /**
     * Constructs MarkDoneCommand.
     *
     * @param index Index of task to be marked as done.
     */
    public MarkDoneCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Marks a task as done by index, outputs message to user and updates the file.
     *
     * @param taskList List of task.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException Throws exception if index is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (isInvalidIndex(taskList.getTotalNumOfTasks())) {
            throw new DukeException("Index is out of bound");
        }
        Task t = taskList.markTaskDone(index);
        storage.writeFile(taskList);
        ui.outputMarkTaskDone(t);
    }
    public boolean isInvalidIndex(int totalNumOfTasks) {
        return index < 0 || index >= totalNumOfTasks;
    }
}
