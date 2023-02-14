package twofive.command;

import java.io.IOException;

import twofive.data.TaskList;
import twofive.exception.InvalidTaskException;
import twofive.storage.Storage;
import twofive.task.Task;
import twofive.ui.TaskContainer;

/**
 * Adds a tag to the given task when command is executed.
 */
public class TagAddCommand extends Command {
    private int taskNum;
    private String tag;

    /**
     * Represents a constructor for the TagAddCommand class.
     *
     * @param taskNum Index of the task.
     * @param tag String representing the tag.
     */
    public TagAddCommand(int taskNum, String tag) {
        this.taskNum = taskNum;
        this.tag = tag;
    }
    /**
     * Adds a tag to a given task.
     *
     * @param tasks List of all current tasks.
     * @param storage Storage for saving or loading tasks.
     * @throws InvalidTaskException if task number is invalid
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidTaskException {
        String commandResult;
        if (this.taskNum < 0 || this.taskNum >= tasks.getTasksNum()) {
            throw new InvalidTaskException();
        }
        assert taskNum >= 0 & taskNum < tasks.getTasksNum() : "Task number should be at least 0 or 1 less than "
                + "the number of tasks";
        Task currentTask = tasks.addTagToTask(taskNum, tag);
        try {
            storage.save(tasks);
            TaskContainer.setTasks(tasks.getTasks());
            commandResult = "TwoFive has added the tag " + tag + " to this task:\n " + currentTask;
        } catch (IOException e) {
            commandResult = e.getMessage();
        }
        return commandResult;
    }
}
