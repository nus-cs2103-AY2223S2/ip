package cluck.commands;

import cluck.exceptions.TaskIndexOutOfBoundsException;
import cluck.messages.Messages;
import cluck.tasks.Task;
import cluck.taskList.TaskList;

public class UnmarkTaskCommand implements Command {
    private final int taskIndex;

    public UnmarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList) {
        try {
            Task unmarkedTask = taskList.unmarkTask(taskIndex);
            return Messages.MESSAGE_UNMARK_SUCCESSFUL + "\n" + unmarkedTask.toString();
        } catch (TaskIndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
}
