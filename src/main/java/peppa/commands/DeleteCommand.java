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

    public DeleteCommand() {}

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        if (this.taskIndex < 0 || this.taskIndex >= taskList.getLength()) {
            throw new PeppaException("Boink! Peppa could not find the requested task. "
                    + "Please enter a valid integer and try again.");
        } else {
            Task task = taskList.retrieveTask(taskIndex);
            taskList.deleteTask(task);
            storage.saveChanges(taskList);
            return Ui.getDeleteTaskMessage(task) + Ui.getTaskSummary(taskList);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
