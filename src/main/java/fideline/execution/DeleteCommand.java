package fideline.execution;

import fideline.exception.DataFileInteractionException;
import fideline.exception.InvalidArgumentException;
import fideline.save.Storage;
import fideline.task.TaskManager;
import fideline.user.Ui;
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
    public void execute(TaskManager taskManager, Storage storage, Ui ui)
            throws DataFileInteractionException, InvalidArgumentException {
        if (taskManager.checkTask(taskNum)) {
            String s = taskManager.deleteTask(taskNum);
            storage.deleteLine(taskNum);
            ui.deleteMsg(s, taskManager.getTaskCount());
        } else {
            throw new InvalidArgumentException("task! (the one given does not exist!)");
        }
    }
}
