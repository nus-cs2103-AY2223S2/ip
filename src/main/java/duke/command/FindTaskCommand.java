package duke.command;

import java.util.List;

import duke.interfaces.Command;
import duke.model.Task;
import duke.model.TaskModel;
import duke.view.cli.TaskView;

/**
 * A command to find all tasks in the task list whose description contains a substring.
 */

public class FindTaskCommand implements Command {
    private final TaskModel taskModel;
    private final TaskView taskView;
    private final String searchStr;

    /**
     * Instantiates a command that displays all tasks containing the search string to the view upon execution.
     * @param taskModel The model containing the task list.
     * @param taskView The current view.
     * @param searchStr The string to search.
     */
    FindTaskCommand(TaskModel taskModel, TaskView taskView, String searchStr) {
        this.taskModel = taskModel;
        this.taskView = taskView;
        this.searchStr = searchStr;
    }

    /**
     * Displays all tasks matching the search description to the view.
     */
    @Override
    public void execute() {
        List<Task> matchingTasks = taskModel.findTasks(this.searchStr);
        taskView.renderTasks(matchingTasks);
    }
}
