package command;

import interfaces.Command;
import model.Task;
import interfaces.View;
import model.TaskModel;

public class AddToDoCommand implements Command {
    private final TaskModel taskModel;
    private final View taskView;
    private final String todoDescription;
    private static final String addedTaskMessage = "Got it. I've added this task:\n  ";
    AddToDoCommand(View taskView, TaskModel taskModel, String todoDescription) {
        this.todoDescription = todoDescription;
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

    @Override
    public void execute() {
        Task newTask = this.taskModel.createTask(todoDescription);
        taskView.showMessage(addedTaskMessage + newTask.toString()
        + String.format("\nNow you have %d tasks in the list.", taskModel.getNumberOfTasks()));
    }
}
