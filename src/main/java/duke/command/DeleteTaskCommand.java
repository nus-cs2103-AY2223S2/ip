package duke.command;
import duke.command.exceptions.CommandExecutionError;
import duke.interfaces.Command;
import duke.interfaces.Model;
import duke.interfaces.View;
import duke.model.Task;

/**
 * Command that deletes tasks.
 */
public class DeleteTaskCommand implements Command {
    private final Model taskModel;
    private final View taskView;
    private final int indexToDelete;
    DeleteTaskCommand(Model taskModel, View taskView, int indexToDelete) {
        this.taskView = taskView;
        this.taskModel = taskModel;
        this.indexToDelete = indexToDelete;
    }
    @Override
    public void execute() throws CommandExecutionError {
        int numTasks = taskView.getNumDisplayedTasks();
        if (indexToDelete >= numTasks) {
            throw new CommandExecutionError(String.format("Only %d tasks displayed", numTasks));
        } else if (indexToDelete < 0) {
            throw new CommandExecutionError("Task index cannot be negative");
        }
        Task taskToDelete = taskView.getDisplayedTask(indexToDelete);
        taskModel.deleteTask(taskToDelete);
        taskView.showMessage("Noted, I've removed this task:\n  " + taskToDelete.toString()
            + String.format("\n Now you have %d tasks in the list.", taskModel.getNumberOfTasks()));
        taskView.setTasks(taskModel.getTasks(), true);
    }
}
