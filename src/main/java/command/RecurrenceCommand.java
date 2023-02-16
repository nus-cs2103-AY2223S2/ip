package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

/**
 * Add recurrence to a task.
 */
public class RecurrenceCommand extends Command {
    private final int index;
    private final String recurrence;

    /**
     * Constructs RecurrenceCommand.
     *
     * @param index Index of task to have recurrence status modified.
     */
    public RecurrenceCommand(String recurrence, int index) {
        this.index = index - 1;
        this.recurrence = recurrence;
    }

    /**
     * Modifies a task as done by index, outputs message to user and updates the file.
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
        if (isInvalidRecurrence()) {
            throw new DukeException("Please enter the right repeating factor");
        }
        Task t = taskList.addRecurrence(index, recurrence);
        storage.writeFile(taskList);
        ui.outputAddRecurrence(t);
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

    /**
     * Checks if the recurrence status is one of the valid options.
     *
     * @return Return true is the recurrence is invalid.
     */
    public boolean isInvalidRecurrence() {
        if (recurrence.equals("daily") || recurrence.equals("weekly")
                || recurrence.equals("monthly") || recurrence.equals("yearly")) {
            return false;
        } else {
            return true;
        }
    }
}
