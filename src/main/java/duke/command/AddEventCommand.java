package duke.command;

import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.command.exceptions.InvalidParameterError;
import duke.command.utils.DateTimeStringParser;
import duke.interfaces.Command;
import duke.interfaces.View;
import duke.model.Task;
import duke.model.TaskModel;

/**
 * Command to create event and add it to the task list.
 */

public class AddEventCommand implements Command {
    private static final String ADDED_TASK_MESSAGE = "Got it. I've added this task:\n  ";
    private final TaskModel taskModel;
    private final View taskView;
    private final String todoDescription;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Instantiates a command that creates the event upon execution.
     * @param taskView The current view.
     * @param taskModel The model that stores all tasks.
     * @param todoDescription A description of the event.
     * @param startTime Event start time formatted as d/MMM/yyyy HH:mm. Time is optional and defaults to NOON (12:00 PM)
     * @param endTime Event end time formatted as d/MMM/yyyy HH:mm. Time is optional and defaults to NOON (12:00 PM)
     * @throws InvalidParameterError If startTime or endTime is formatted improperly.
     */
    AddEventCommand(View taskView, TaskModel taskModel, String todoDescription,
                    String startTime, String endTime) throws InvalidParameterError {
        this.todoDescription = todoDescription;
        LocalTime defaultEventTime = LocalTime.NOON;
        this.startTime = DateTimeStringParser.parseDateTimeString(startTime, defaultEventTime);
        this.endTime = DateTimeStringParser.parseDateTimeString(endTime, defaultEventTime);
        this.taskView = taskView;
        this.taskModel = taskModel;
    }

    /**
     * Creates a new event, adds it to the task list and displays an appropriate message to the user.
     */
    @Override
    public void execute() {
        Task newTask = this.taskModel.createTask(todoDescription, startTime, endTime);
        taskView.showMessage(ADDED_TASK_MESSAGE + newTask.toString()
            + String.format("\nNow you have %d tasks in the list.", taskModel.getNumberOfTasks()));
    }
}
