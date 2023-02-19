package fideline.execution;

import fideline.exception.DataFileInteractionException;
import fideline.exception.InvalidArgumentException;
import fideline.save.Storage;
import fideline.task.TaskManager;
import fideline.user.Ui;

/**
 * Command that executes the removal of a task from the task list.
 *
 * @author Fun Leon
 */
public class DeleteCommand extends Command {

    private int taskNum;
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Removes an existing task from task list and memory.
     *
     * @param taskManager Manager for existing tasks and creation of new ones.
     * @param storage     Handler for storage of existing tasks locally.
     * @param ui          Handler for display messages to the user.
     */
    @Override
    public String execute(TaskManager taskManager, Storage storage, Ui ui)
            throws DataFileInteractionException, InvalidArgumentException {
        if (!taskManager.isValidTask(taskNum)) {
            throw new InvalidArgumentException("task");
        }
        String s = taskManager.getTaskString(taskNum);
        taskManager.deleteTask(taskNum);
        storage.deleteLine(taskNum);
        return ui.getDeleteMsg(s, taskManager.getTaskCount());
    }
}
