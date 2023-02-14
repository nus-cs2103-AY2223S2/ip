package twofive.command;

import java.io.IOException;

import twofive.data.TaskList;
import twofive.exception.InvalidTaskException;
import twofive.exception.TaskDoneException;
import twofive.storage.Storage;
import twofive.task.Task;
import twofive.ui.TaskContainer;

/**
 * Marks a task as done given its number in a list of tasks
 * when command is executed.
 */
public class MarkCommand extends Command {
    private int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks a task as done from the given list of tasks using the provided task number.
     *
     * @param tasks   List of tasks containing the task to be marked as done.
     * @param storage Storage for saving or loading tasks.
     * @throws InvalidTaskException if task number is invalid
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskDoneException, InvalidTaskException {
        String commandResult = "";
        if (this.taskNum < 0 || this.taskNum >= tasks.getTasksNum()) {
            throw new InvalidTaskException();
        }
        assert taskNum >= 0 & taskNum < tasks.getTasksNum() : "Task number should be at least 0 or 1 less than "
                + "the number of tasks";
        Task currentTask = tasks.setTaskAsDone(taskNum);
        try {
            storage.save(tasks);
            TaskContainer.setTasks(tasks.getTasks());
            commandResult = "Bravo! Congratulations for completing this task:\n " + currentTask;
        } catch (IOException e) {
            commandResult = e.getMessage();
        }
        return commandResult;
    }
}
