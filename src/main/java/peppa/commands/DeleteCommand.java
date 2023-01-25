package peppa.commands;

import peppa.*;

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
    public void execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        if (this.taskIndex < 0 || this.taskIndex >= taskList.getLength()) {
            throw new PeppaException("Boink! Peppa could not find the requested task. " +
                    "Please enter a valid integer and try again.");
        } else {
            Task task = taskList.retrieveTask(taskIndex);
            taskList.deleteTask(task);
            Ui.displayDeleteTaskMessage(task);
            Ui.displayTaskSummary(taskList);
            storage.saveChanges(taskList);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
