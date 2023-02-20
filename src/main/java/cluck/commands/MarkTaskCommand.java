package cluck.commands;

import cluck.exceptions.TaskIndexOutOfBoundsException;
import cluck.messages.Messages;
import cluck.tasks.Task;
import cluck.taskList.TaskList;

public class MarkTaskCommand implements Command {
    private final int taskIndex;

    public MarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList) {
        try {
            Task markedTask = taskList.markTask(taskIndex);
            return Messages.MESSAGE_MARK_SUCCESSFUL + "\n" + markedTask.toString();
        } catch (TaskIndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
}
