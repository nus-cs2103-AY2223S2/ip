package peppa.commands;

import peppa.PeppaException;
import peppa.Storage;
import peppa.Task;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents an unmark task as done command.
 */
public class UnmarkCommand implements Command {
    public static final String COMMAND_WORD = "unmark";
    private int taskIndex;

    /**
     * Constructs an unmark command with the index of the task to be unmarked as done.
     * Tasks are zero-indexed.
     *
     * @param taskIndex Index of the task to be unmarked as done. Should be >= 0 and < length of the tasklist.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        if (this.taskIndex >= 0 && this.taskIndex < taskList.getLength()) {
            Task task = taskList.retrieveTask(taskIndex);
            task.setDone(false);
            storage.saveChanges(taskList);
            return Ui.getUnmarkDoneMessage(task);
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
