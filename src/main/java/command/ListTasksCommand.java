package command;

import interfaces.Command;
import interfaces.Model;
import interfaces.View;

public class ListTasksCommand implements Command {
    private final Model taskModel;
    private final View taskView;
    ListTasksCommand(Model taskModel, View taskView) {
        this.taskModel = taskModel;
        this.taskView = taskView;
    }
    @Override
    public void execute() {
        taskView.renderTasks(taskModel.getTasks());
    }
}
