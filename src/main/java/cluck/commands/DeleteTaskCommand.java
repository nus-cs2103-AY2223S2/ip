package cluck.commands;

import cluck.exceptions.TaskIndexOutOfBoundsException;
import cluck.messages.Messages;
import cluck.tasks.Task;
import cluck.taskList.TaskList;

public class DeleteTaskCommand implements Command {
    private final int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList) {
        try {
            Task deletedTask = taskList.deleteTask(taskIndex);
            return Messages.MESSAGE_DELETE_SUCCESSFUL + "\n" + deletedTask;
        } catch (TaskIndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
}
