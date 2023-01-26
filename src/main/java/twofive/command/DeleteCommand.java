package twofive.command;

import java.io.IOException;

import twofive.data.TaskList;
import twofive.exception.InvalidTaskException;
import twofive.storage.Storage;
import twofive.task.Task;
import twofive.ui.Ui;

/**
 * Deletes a task given its number in a list of tasks when command is executed.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes a task from the given list of tasks using the provided task number.
     *
     * @param tasks List of tasks to delete the task from.
     * @param ui UI interacting with user.
     * @param storage Storage for saving or loading tasks.
     * @throws InvalidTaskException if task number is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskException {
        if (this.taskNum < 0 || this.taskNum >= tasks.getTasksNum()) {
            throw new InvalidTaskException();
        } else {
            Task currentTask = tasks.deleteTask(this.taskNum);
            try {
                storage.save(tasks);
                ui.showMessage("Noted. I've removed this task:\n " + currentTask + "\n"
                        + "Now you have " + tasks.getTasksNum() + " tasks in the list");
            } catch (IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
