package james.command;

import james.JamesException;
import james.task.Task;

/**
 * The command to mark a task as not done.
 */
public class addUnmarkCommand extends Command {
    private int taskNumber;

    public addUnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute() throws JamesException {
        if (taskNumber >= taskList.getSize()) {
            throw new JamesException("Task number is out of range!");
        }
        Task task = taskList.getTask(taskNumber);
        task.markUnDone();
        ui.unmarkTask(task);
    }
}

