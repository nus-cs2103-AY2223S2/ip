package cluck.commands;

import cluck.exceptions.TaskIndexOutOfBoundsException;
import cluck.messages.Messages;
import cluck.tasklist.TaskList;
import cluck.tasks.Task;

/**
 * Mark task command when executed marks the task at the given index.
 * If execution successful it returns the success message and the marked task.
 * If index given is out of the task list's range it returns the error message instead.
 */
public class MarkTaskCommand implements Command {
    private final int taskIndex;

    /**
     * Instantiates a new Mark task command.
     *
     * @param taskIndex the task index
     */
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
