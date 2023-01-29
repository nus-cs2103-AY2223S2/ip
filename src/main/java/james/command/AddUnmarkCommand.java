package james.command;

import james.JamesException;
import james.task.Task;

public class AddUnmarkCommand extends Command {
    private int taskNumber;

    public AddUnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute() throws JamesException {
        if (taskNumber >= taskList.getSize()) {
            throw new JamesException("Task number is out of range!");
        }
        Task task = taskList.getTask(taskNumber);
        task.markUndone();
        ui.unmarkTask(task);
    }
}

