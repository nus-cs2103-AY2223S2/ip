package twofive.command;

import java.io.IOException;

import twofive.data.TaskList;
import twofive.exception.InvalidTaskException;
import twofive.exception.TaskUndoneException;
import twofive.storage.Storage;
import twofive.task.Task;
import twofive.ui.Ui;

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
     * @param ui UI interacting with user.
     * @param storage Storage for saving or loading tasks.
     * @throws InvalidTaskException if task number is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskUndoneException, InvalidTaskException {
        if (this.taskNum < 0 || this.taskNum >= tasks.getTasksNum()) {
            throw new InvalidTaskException();
        } else {
            Task currentTask = tasks.setTaskAsUndone(taskNum);
            try {
                storage.save(tasks);
                ui.showMessage("OK, I've marked this task as not done yet:\n " + currentTask);
            } catch (IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
