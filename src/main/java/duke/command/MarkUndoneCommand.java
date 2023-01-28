package duke.command;

import duke.command.exceptions.CommandExecutionError;
import duke.interfaces.Command;
import duke.interfaces.View;
import duke.model.TaskModel;

public class MarkUndoneCommand implements Command {
    private final TaskModel taskModel;
    private final View taskView;
    private final int taskIndex;
    private static final String markedUndoneMessage = "OK, I've marked this task as not done yet:\n";
    MarkUndoneCommand(View taskView, TaskModel taskModel, int index) {
        this.taskIndex = index;
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

    @Override
    public void execute() throws CommandExecutionError {
        int numTasks = taskModel.getNumberOfTasks();
        if (taskIndex >= numTasks) {
            throw new CommandExecutionError(String.format("You have only %d tasks", numTasks));
        } else if (taskIndex < 0) {
            throw new CommandExecutionError("index cannot be negative");
        }
        this.taskModel.markTaskUndone(this.taskIndex);
        taskView.showMessage(markedUndoneMessage + taskModel.getTask(taskIndex).toString());
    }
}
