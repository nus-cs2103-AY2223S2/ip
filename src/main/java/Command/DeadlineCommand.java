package Command;

import Task.TaskList;
import Task.Task;

/**
 * Command class for Deadline object
 */
public class DeadlineCommand implements Command {
    private Task newDeadline;

    public DeadlineCommand(Task t) {
        newDeadline = t;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.add(newDeadline);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
