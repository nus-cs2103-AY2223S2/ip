package command;

import interfaces.Command;
import interfaces.Model;
import interfaces.View;
import model.TaskModel;

import java.util.List;

public class MarkUndoneCommand implements Command {
    private final TaskModel taskModel;
    private final View taskView;
    private final int taskIndex;
    private static final String markedUndoneMessage = "OK, I've marked this task as not done yet:\n";
    MarkUndoneCommand(View taskView, TaskModel taskModel, int index) {
        this.taskIndex = index - 1; // 1 indexed
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

    @Override
    public void execute() {
        this.taskModel.markTaskUndone(this.taskIndex);
        taskView.showMessage(markedUndoneMessage + taskModel.getTask(taskIndex).toString());
    }
}
