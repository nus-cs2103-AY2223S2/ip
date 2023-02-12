package duke.command;

import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.command.exceptions.InvalidParameterError;
import duke.command.utils.DateTimeStringParser;
import duke.interfaces.Command;
import duke.model.TaskModel;
import duke.view.TaskView;

public class ListTasksCommand implements Command {
    private final TaskModel taskModel;
    private final TaskView taskView;

    private LocalDateTime offset = null;
    ListTasksCommand(TaskModel taskModel, TaskView taskView) {
        this.taskModel = taskModel;
        this.taskView = taskView;
    }

    ListTasksCommand(TaskModel taskModel, TaskView taskView, String timeOffset) throws InvalidParameterError {
        this.taskModel = taskModel;
        this.taskView = taskView;
        LocalTime defaultTime = LocalTime.MAX;
        this.offset = DateTimeStringParser.parseDateTimeString(timeOffset, defaultTime);
    }
    @Override
    public void execute() {
        if (offset != null) {

            taskView.renderTasks(taskModel.getTasksOn(offset));
        } else {
            taskView.renderTasks(taskModel.getTasks());
        }
    }
}
