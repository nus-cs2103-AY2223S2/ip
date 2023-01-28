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
        int numTasks = taskModel.getNumberOfTasks();
        if (indexToDelete >= numTasks || indexToDelete < 0) {
            throw new CommandExecutionError(String.format("You have only %d tasks", numTasks));
        }
        Task taskToDelete = taskModel.getTask(indexToDelete);
        taskModel.deleteTask(indexToDelete);
        taskView.showMessage("Noted, I've removed this task:\n  " + taskToDelete.toString()
        + String.format("\n Now you have %d tasks in the list.", taskModel.getNumberOfTasks()));
    }
}
