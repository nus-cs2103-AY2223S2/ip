package command;

import interfaces.Command;
import interfaces.View;
import model.Task;
import model.TaskModel;

public class AddEventCommand implements Command {
    private final TaskModel taskModel;
    private final View taskView;
    private final String todoDescription;
    private final String startTime;
    private final String endTime;
    private static final String addedTaskMessage = "Got it. I've added this task:\n  ";
    AddEventCommand(View taskView, TaskModel taskModel, String todoDescription, String startTime, String endTime) {
        this.todoDescription = todoDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

    @Override
    public void execute() {
        Task newTask = this.taskModel.createTask(todoDescription, startTime, endTime);
        taskView.showMessage(addedTaskMessage + newTask.toString()
        + String.format("\nNow you have %d tasks in the list.", taskModel.getNumberOfTasks()));
    }
}
