package duke.command;

import duke.interfaces.Command;
import duke.interfaces.View;
import duke.model.Task;
import duke.model.TaskModel;

public class AddToDoCommand implements Command {
    private static final String ADDED_TASK_MESSAGE = "Got it. I've added this task:\n  ";
    private final TaskModel taskModel;
    private final View taskView;
    private final String todoDescription;
    AddToDoCommand(View taskView, TaskModel taskModel, String todoDescription) {
        this.todoDescription = todoDescription;
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

    @Override
    public void execute() {
        Task newTask = this.taskModel.createTask(todoDescription);
        taskView.showMessage(ADDED_TASK_MESSAGE + newTask.toString()
            + String.format("\nNow you have %d tasks in the list.", taskModel.getNumberOfTasks()));
    }
}
