package twofive.command;

import java.io.IOException;

import twofive.data.TaskList;
import twofive.exception.InvalidTaskException;
import twofive.storage.Storage;
import twofive.task.Task;
import twofive.ui.TaskContainer;

/**
 * Deletes a task given its number in a list of tasks when command is executed.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int t) {
        taskNum = t;
    }

    /**
     * Deletes a task from the given list of tasks using the provided task number.
     *
     * @param tasks List of tasks to delete the task from.
     * @param storage Storage for saving or loading tasks.
     * @throws InvalidTaskException if task number is invalid
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidTaskException {
        String commandResult = "";
        if (taskNum < 0 || taskNum >= tasks.getTasksNum()) {
            throw new InvalidTaskException();
        }
        assert taskNum >= 0 & taskNum < tasks.getTasksNum() : "Task number should be at least 0 or 1 less than "
                + "the number of tasks";
        Task currentTask = tasks.deleteTask(taskNum);
        try {
            storage.save(tasks);
            TaskContainer.setTasks(tasks.getTasks());
            commandResult = "TwoFive has removed this task for you:\n " + currentTask + "\n"
                    + "Now you have " + tasks.getTasksNum() + " tasks in the list";
        } catch (IOException e) {
            commandResult = e.getMessage();
        }
        return commandResult;
    }
}
