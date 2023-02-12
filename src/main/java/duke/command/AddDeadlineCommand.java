package duke.command;

import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.command.exceptions.InvalidParameterError;
import duke.command.utils.DateTimeStringParser;
import duke.interfaces.Command;
import duke.interfaces.View;
import duke.model.Task;
import duke.model.TaskModel;

public class AddDeadlineCommand implements Command {
    private static final String ADDED_TASK_MESSAGE = "Got it. I've added this task:\n  ";
    private final TaskModel taskModel;
    private final View taskView;
    private final String todoDescription;
    private final LocalDateTime deadline;
    AddDeadlineCommand(View taskView, TaskModel taskModel, String todoDescription, String deadline)
            throws InvalidParameterError {
        this.todoDescription = todoDescription;
        this.taskView = taskView;
        this.taskModel = taskModel;
        LocalTime defaultDeadlineTime = LocalTime.MAX;
        this.deadline = DateTimeStringParser.parseDateTimeString(deadline, defaultDeadlineTime);
    }


    @Override
    public void execute() {
        Task newTask = this.taskModel.createTask(todoDescription, deadline);
        taskView.showMessage(ADDED_TASK_MESSAGE + newTask.toString()
            + String.format("\nNow you have %d tasks in the list.", taskModel.getNumberOfTasks()));
    }
}
