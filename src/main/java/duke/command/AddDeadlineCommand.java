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
 * Command for adding a deadline to the task list.
 */
public class AddDeadlineCommand implements Command {
    private static final String ADDED_TASK_MESSAGE = "Got it. I've added this task:\n  ";
    private final TaskModel taskModel;
    private final View taskView;
    private final String todoDescription;
    private final LocalDateTime deadline;

    private static final String addedTaskMessage = "Got it. I've added this task:\n  ";

    /**
     * Instantiates a command which creates the deadline on execution.
     * @param taskView The current view.
     * @param taskModel The model that stores all tasks.
     * @param todoDescription Description of the deadline.
     * @param deadline The deadline, formatted as d/MMM/yyyy HH:mm, time is optional and defaults to 23:59
     * @throws InvalidParameterError if deadline is formatted improperly.
     */
    AddDeadlineCommand(View taskView, TaskModel taskModel, String todoDescription, String deadline)
            throws InvalidParameterError {
        this.todoDescription = todoDescription;
        this.taskView = taskView;
        this.taskModel = taskModel;
        LocalTime defaultDeadlineTime = LocalTime.MAX;
        this.deadline = DateTimeStringParser.parseDateTimeString(deadline, defaultDeadlineTime);
    }

    /**
     * Creates a new deadline, saves it to the model and displays an appropriate message.
     */
    @Override
    public void execute() {
        Task newTask = this.taskModel.createTask(todoDescription, deadline);
        taskView.showMessage(ADDED_TASK_MESSAGE + newTask.toString()
            + String.format("\nNow you have %d tasks in the list.", taskModel.getNumberOfTasks()));
    }
}
