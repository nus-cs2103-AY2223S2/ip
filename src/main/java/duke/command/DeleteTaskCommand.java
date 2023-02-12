package duke.command;

import duke.command.exceptions.CommandExecutionError;
import duke.interfaces.Command;
import duke.model.Task;
import duke.model.TaskModel;
import duke.view.TaskView;

public class DeleteTaskCommand implements Command {
    private final TaskModel taskModel;
    private final TaskView taskView;
    private final int indexToDelete;
    DeleteTaskCommand(TaskView taskView, TaskModel taskModel, int indexToDelete) {
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
    }
}
