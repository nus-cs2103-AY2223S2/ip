package duke.command;

import duke.command.exceptions.CommandExecutionError;
import duke.interfaces.Command;
import duke.model.TaskModel;
import duke.view.TaskView;

public class MarkDoneCommand implements Command {
    private static final String MARKED_DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    private final TaskModel taskModel;
    private final TaskView taskView;
    private final int taskIndex;
    MarkDoneCommand(TaskView taskView, TaskModel taskModel, int index) {
        this.taskIndex = index;
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

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
