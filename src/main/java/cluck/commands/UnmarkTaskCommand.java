package cluck.commands;

import cluck.exceptions.TaskIndexOutOfBoundsException;
import cluck.messages.Messages;
import cluck.tasks.Task;
import cluck.taskList.TaskList;

/**
 * Un-mark task command when executed marks the task at the given index.
 * If execution successful it returns the success message and the un-marked task.
 * If index given is out of the task list's range it returns the error message instead.
 */
public class UnmarkTaskCommand implements Command {
    private final int taskIndex;

    /**
     * Instantiates a new Unmark task command.
     *
     * @param taskIndex the task index
     */
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
