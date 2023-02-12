package duke.command;

import java.util.List;

import duke.command.exceptions.InvalidParameterError;
import duke.interfaces.Command;
import duke.model.Task;
import duke.model.TaskModel;
import duke.view.TaskView;

public class FindTaskCommand implements Command {
    private final TaskModel taskModel;
    private final TaskView taskView;
    private final String searchStr;
    FindTaskCommand(TaskModel taskModel, TaskView taskView, String searchStr) throws InvalidParameterError {
        this.taskModel = taskModel;
        this.taskView = taskView;
        this.searchStr = searchStr;
    }
    @Override
    public void execute() {
        List<Task> matchingTasks = taskModel.findTasks(this.searchStr);
        taskView.renderTasks(matchingTasks);
    }
}
