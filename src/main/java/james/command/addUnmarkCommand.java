package james.command;

import james.JamesException;
import james.task.Task;

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof addUnmarkCommand) {
            return taskNumber == ((addUnmarkCommand) obj).taskNumber;
        }
        return false;
    }
}

