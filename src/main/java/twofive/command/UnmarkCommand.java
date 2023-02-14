package twofive.command;

import java.io.IOException;

import twofive.data.TaskList;
import twofive.exception.InvalidTaskException;
import twofive.exception.TaskUndoneException;
import twofive.storage.Storage;
import twofive.task.Task;
import twofive.ui.TaskContainer;

/**
 * Marks a task as not done given its number in a list of tasks
 * when command is executed.
 */
public class UnmarkCommand extends Command {
    private int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks a task asnot  done from the given list of tasks using the provided task number.
     *
     * @param tasks List of tasks containing the task to be marked as not done.
     * @param storage Storage for saving or loading tasks.
     * @throws InvalidTaskException if task number is invalid
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskUndoneException, InvalidTaskException {
        String commandResult = "";
        if (this.taskNum < 0 || this.taskNum >= tasks.getTasksNum()) {
            throw new InvalidTaskException();
        }
        assert taskNum >= 0 & taskNum < tasks.getTasksNum() : "Task number should be at least 0 or 1 less than "
                + "the number of tasks";
        Task currentTask = tasks.setTaskAsUndone(taskNum);
        try {
            storage.save(tasks);
            TaskContainer.setTasks(tasks.getTasks());
            commandResult = "TwoFive has marked this task as undone for you:\n " + currentTask;
        } catch (IOException e) {
            commandResult = e.getMessage();
        }
        return commandResult;
    }
}
