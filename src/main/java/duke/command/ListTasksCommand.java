package duke.command;

import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.command.exceptions.InvalidParameterError;
import duke.command.utils.DateTimeStringParser;
import duke.interfaces.Command;
import duke.interfaces.Model;
import duke.interfaces.View;

/**
 * A command that lists all tasks. Optionally, this command can list all tasks due before a time.
 */
public class ListTasksCommand implements Command {
    private final Model taskModel;
    private final View taskView;

    private LocalDateTime offset = null;

    /**
     * Instantiates a command that lists all tasks.
     * @param taskModel The model that stores the task list.
     * @param taskView The current view.
     */
    ListTasksCommand(Model taskModel, View taskView) {
        this.taskModel = taskModel;
        this.taskView = taskView;
    }

    /**
     * Instantiates a command that lists all tasks due on or before the supplied time offset.
     * @param taskModel The model that stores the task list.
     * @param taskView The current view.
     * @param timeOffset The time offset formatted as d/MMM/yyyy with an optional HH:mm (defaults to 23:59)
     * @throws InvalidParameterError If the time offset is improperly formatted.
     */

    ListTasksCommand(Model taskModel, View taskView, String timeOffset) throws InvalidParameterError {
        this.taskModel = taskModel;
        this.taskView = taskView;
        LocalTime defaultTime = LocalTime.MAX;
        this.offset = DateTimeStringParser.parseDateTimeString(timeOffset, defaultTime);
    }

    /**
     * If time offset was not supplied, displays all tasks on the view. Otherwise, it displays
     * tasks due on or before the time offset supplied to the view.
     */
    @Override
    public void execute() {
        if (offset != null) {
            taskView.setTasks(taskModel.getTasksOn(offset), true);
        } else {
            taskView.setTasks(taskModel.getTasks(), true);
        }
    }
}
