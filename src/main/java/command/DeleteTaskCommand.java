package command;

import interfaces.Command;
import model.Task;
import model.TaskModel;
import view.TaskView;

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
    public void execute() {
        Task taskToDelete = taskModel.getTask(indexToDelete);
        taskModel.deleteTask(indexToDelete);
        taskView.showMessage("Noted, I've removed this task:\n  " + taskToDelete.toString()
        + String.format("\n Now you have %d tasks in the list.", taskModel.getNumberOfTasks()));
    }
}
