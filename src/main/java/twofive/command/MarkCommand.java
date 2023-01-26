package twofive.command;

import java.io.IOException;

import twofive.data.TaskList;
import twofive.exception.InvalidTaskException;
import twofive.exception.TaskDoneException;
import twofive.storage.Storage;
import twofive.task.Task;
import twofive.ui.Ui;

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
     * @param tasks List of tasks containing the task to be marked as done.
     * @param ui UI interacting with user.
     * @param storage Storage for saving or loading tasks.
     * @throws InvalidTaskException if task number is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskDoneException, InvalidTaskException {
        if (this.taskNum < 0 || this.taskNum >= tasks.getTasksNum()) {
            throw new InvalidTaskException();
        } else {
            Task currentTask = tasks.setTaskAsDone(taskNum);
            try {
                storage.save(tasks);
                ui.showMessage("Nice! Congrats for completing this task:\n " + currentTask);
            } catch (IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
