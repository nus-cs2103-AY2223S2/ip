package cluck.commands;

import cluck.exceptions.TaskIndexOutOfBoundsException;
import cluck.messages.Messages;
import cluck.storage.Storage;
import cluck.tasklist.TaskList;
import cluck.tasks.Task;

/**
 * Un-mark task command when executed marks the task at the given index.
 * If execution successful it returns the success message and the un-marked task.
 * If index given is out of the task list's range it returns the error message instead.
 */
public class UnmarkTaskCommand implements Command {
    private final int taskIndex;

    /**
     * Instantiates a new Un-mark task command.
     *
     * @param taskIndex The task index.
     */
    public UnmarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Task unmarkedTask = taskList.unmarkTask(taskIndex);
            storage.writetoSave(taskList);
            return Messages.MESSAGE_UNMARK_SUCCESSFUL + "\n" + unmarkedTask.toString();
        } catch (TaskIndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
}
