package command;

import interfaces.Command;
import interfaces.Model;
import interfaces.View;
import model.TaskModel;

import java.util.List;

public class MarkDoneCommand implements Command {
    private final TaskModel taskModel;
    private final View taskView;
    private final int taskIndex;
    private static final String markedDoneMessage = "Nice! I've marked this task as done:\n";
    MarkDoneCommand(View taskView, TaskModel taskModel, int index) {
        this.taskIndex = index - 1; // 1 indexed
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

    @Override
    public void execute() {
        this.taskModel.markTaskDone(this.taskIndex);
        taskView.showMessage(markedDoneMessage + taskModel.getTask(taskIndex).toString());
    }
}
