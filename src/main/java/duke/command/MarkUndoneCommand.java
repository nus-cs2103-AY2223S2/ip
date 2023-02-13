package duke.command;
import duke.command.exceptions.CommandExecutionError;
import duke.interfaces.Command;
import duke.model.TaskModel;
import duke.view.cli.TaskView;

/**
 * Command to mark a task as undone.
 */
public class MarkUndoneCommand implements Command {
    private static final String MARKED_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:\n";
    private final TaskModel taskModel;
    private final TaskView taskView;
    private final int taskIndex;

    /**
     * Instantiates a command that marks the task at the specified index of the displayed tasks as undone.
     * @param taskView The current view.
     * @param taskModel Model that stores the task list.
     * @param index Index of task to mark as undone.
     */
    MarkUndoneCommand(TaskView taskView, TaskModel taskModel, int index) {
        this.taskIndex = index;
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

    /**
     * Mark the task at taskIndex as undone.
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
        this.taskModel.markTaskUndone(taskView.getDisplayedTask(taskIndex));
        taskView.showMessage(MARKED_UNDONE_MESSAGE + taskModel.getTask(taskIndex).toString());
    }
}
