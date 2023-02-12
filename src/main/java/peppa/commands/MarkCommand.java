package peppa.commands;

import peppa.PeppaException;
import peppa.Storage;
import peppa.Task;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents a mark task as done command.
 */
public class MarkCommand implements Command {
    public static final String COMMAND_WORD = "mark";
    private int taskIndex;

    /**
     * Constructs a mark command with the index of the task to be marked as done.
     * Tasks are zero-indexed.
     *
     * @param taskIndex Index of the task to be marked as done. Should be >= 0 and < length of the tasklist.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        if (this.taskIndex >= 0 && this.taskIndex < taskList.getLength()) {
            Task task = taskList.retrieveTask(taskIndex);
            task.setDone(true);
            storage.saveChanges(taskList);
            return Ui.getMarkDoneMessage(task);
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
