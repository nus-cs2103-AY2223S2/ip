package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

/**
 * Deletes a task.
 */
public class DeleteTaskCommand extends Command {

    private final int index;

    /**
     * Constructs a DeleteTaskCommand.
     *
     * @param index Index of task to be deleted.
     */
    public DeleteTaskCommand(int index) {

        this.index = index - 1;
    }

    /**
     * Deletes a task by index, outputs message to user and updates the file.
     *
     * @param taskList List of task.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException Throws exception if user input is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (isInvalidIndex(taskList.getTotalNumOfTasks())) {
            throw new DukeException("Index is out of bound");
        }
        Task t = taskList.deleteTask(index);
        storage.writeFile(taskList);
        ui.outputDeleteTask(t);
    }

    /**
     * Checks if the index is within the bounds of the taskList.
     *
     * @param totalNumOfTasks The total number of task in the taskList.
     * @return Return true is the index is out of bounds.
     */
    public boolean isInvalidIndex(int totalNumOfTasks) {
        return index < 0 || index >= totalNumOfTasks;
    }
}
