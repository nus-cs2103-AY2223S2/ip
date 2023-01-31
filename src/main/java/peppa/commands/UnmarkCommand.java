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

    public UnmarkCommand() {}

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        if (this.taskIndex < 0 || this.taskIndex >= taskList.getLength()) {
            throw new PeppaException("Boink! Peppa could not find the requested task. "
                    + "Please enter a valid integer and try again.");
        } else {
            Task task = taskList.retrieveTask(taskIndex);
            task.setDone(false);
            storage.saveChanges(taskList);
            return Ui.getUnmarkDoneMessage(task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
