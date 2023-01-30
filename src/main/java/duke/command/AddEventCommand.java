package duke.command;

import duke.command.exceptions.InvalidParameterError;
import duke.command.utils.DateTimeStringParser;
import duke.interfaces.Command;
import duke.interfaces.View;
import duke.model.Task;
import duke.model.TaskModel;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddEventCommand implements Command {
    private final TaskModel taskModel;
    private final View taskView;
    private final String todoDescription;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private static final String addedTaskMessage = "Got it. I've added this task:\n  ";
    AddEventCommand(View taskView, TaskModel taskModel, String todoDescription,
                    String startTime, String endTime) throws InvalidParameterError {
        this.todoDescription = todoDescription;
        LocalTime defaultEventTime = LocalTime.NOON;
        this.startTime = DateTimeStringParser.parseDateTimeString(startTime, defaultEventTime);
        this.endTime = DateTimeStringParser.parseDateTimeString(endTime, defaultEventTime);
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
