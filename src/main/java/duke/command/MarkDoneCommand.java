package duke.command;

import duke.command.exceptions.CommandExecutionError;
import duke.interfaces.Command;
import duke.model.TaskModel;
import duke.view.TaskView;

/**
 * Command to mark a task done.
 */
public class MarkDoneCommand implements Command {
    private static final String MARKED_DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    private final TaskModel taskModel;
    private final TaskView taskView;
    private final int taskIndex;

    /**
     * Instantiates a command that marks the task at the specified index of the displayed tasks as done.
     * @param taskView The current view.
     * @param taskModel Model that stores the task list.
     * @param index Index of task to mark as done.
     */
    MarkDoneCommand(TaskView taskView, TaskModel taskModel, int index) {
        this.taskIndex = index;
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

    /**
     * Mark the task at taskIndex as done.
     * @throws CommandExecutionError if supplied index is negative or greater than the number of displayed tasks
     */

    @Override
    public void execute() throws CommandExecutionError {
        int numTasks = taskView.getNumDisplayedTasks();
        if (taskIndex >= numTasks) {
            throw new CommandExecutionError(String.format("Only %d tasks displayed", numTasks));
        } else if (taskIndex < 0) {
            throw new CommandExecutionError("index cannot be negative");
        }
        this.taskModel.markTaskDone(taskView.getDisplayedTask(taskIndex));
        taskView.showMessage(MARKED_DONE_MESSAGE + taskModel.getTask(taskIndex).toString());
    }
}
