package command;

import interfaces.Command;
import interfaces.Model;
import model.Task;
import interfaces.View;

import java.util.List;

public class AddToListCommand implements Command {
    private final Model taskModel;
    private final View taskView;
    private final Task task;
    private static final String addedTaskMessage = "added: ";
    AddToListCommand(View taskView, Model taskModel, Task task) {
        this.task = task;
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

    @Override
    public void execute() {
        this.taskModel.addTask(task);
        taskView.showMessage(addedTaskMessage + task.getDescription());
    }
}
