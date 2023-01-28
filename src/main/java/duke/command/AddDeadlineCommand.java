package duke.command;

import duke.interfaces.Command;
import duke.interfaces.View;
import duke.model.Task;
import duke.model.TaskModel;

public class AddDeadlineCommand implements Command {
    private final TaskModel taskModel;
    private final View taskView;
    private final String todoDescription;
    private final String deadline;
    private static final String addedTaskMessage = "Got it. I've added this task:\n  ";
    AddDeadlineCommand(View taskView, TaskModel taskModel, String todoDescription, String deadline) {
        this.todoDescription = todoDescription;
        this.deadline = deadline;
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

    @Override
    public void execute() {
        Task newTask = this.taskModel.createTask(todoDescription, deadline);
        taskView.showMessage(addedTaskMessage + newTask.toString()
        + String.format("\nNow you have %d tasks in the list.", taskModel.getNumberOfTasks()));
    }
}
