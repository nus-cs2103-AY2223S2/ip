package fea.commands;

import fea.exceptions.FeaException;
import fea.exceptions.TaskException;
import fea.storage.Storage;
import fea.tasklist.TaskList;
import fea.ui.Ui;
/**
 * DeleteCommand class that implements the Command interface.
 */

public class DeleteCommand implements Command {

    private static final String INVALID_TASK_NUMBER = "Please enter a valid task number!";
    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Deletes a task in the task list.
     *
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @return String The string of the deleted task.
     * @throws FeaException If there is an exception due to invalid input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FeaException {
        try {
            int taskNum = Integer.parseInt(fullCommand.substring(7));
            if (taskNum > tasks.size() || taskNum < 1) {
                throw new TaskException(INVALID_TASK_NUMBER);
            }
            return tasks.deleteTask(taskNum, storage, ui);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new TaskException(INVALID_TASK_NUMBER);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
