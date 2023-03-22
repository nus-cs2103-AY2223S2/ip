package cluck.commands;

import cluck.exceptions.TaskIndexOutOfBoundsException;
import cluck.messages.Messages;
import cluck.storage.Storage;
import cluck.tasklist.TaskList;
import cluck.tasks.Task;

/**
 * Delete task command is instantiated when user gives a command to delete a task in the correct format.
 * When executed successfully the command returns a success message, the deleted task, and the new number of tasks in
 * the task list.
 */
public class DeleteTaskCommand implements Command {
    private final int taskIndex;

    /**
     * Instantiates a new Delete task command.
     *
     * @param taskIndex The task index.
     */
    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Task deletedTask = taskList.deleteTask(taskIndex);
            storage.writetoSave(taskList);
            return Messages.MESSAGE_DELETE_SUCCESSFUL + "\n" + deletedTask + "\n"
                    + String.format(Messages.MESSAGE_LIST_COUNT, taskList.taskCount());
        } catch (TaskIndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
}
