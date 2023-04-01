package peppa.commands;

import peppa.PeppaException;
import peppa.Storage;
import peppa.Task;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents a delete task command that removes the task from the tasklist.
 */
public class DeleteCommand implements Command {
    public static final String COMMAND_WORD = "delete";
    private int taskIndex;

    /**
     * Constructs a delete command with the index of the task to be deleted.
     * Tasks are zero-indexed.
     *
     * @param taskIndex Index of the task to be deleted. Should be >= 0 and < length of the tasklist.
     */
    public DeleteCommand(int taskIndex) {

        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        if (this.taskIndex >= 0 && this.taskIndex < taskList.getLength()) {
            Task task = taskList.retrieveTask(taskIndex);
            taskList.deleteTask(task);
            storage.saveChanges(taskList);
            return Ui.getDeleteTaskMessage(task) + Ui.getTaskSummary(taskList);
        } else {
            throw new PeppaException("Boink! Peppa could not find the requested task. "
                    + "Please enter a valid integer and try again.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
