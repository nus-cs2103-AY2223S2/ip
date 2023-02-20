package duke.command;

import java.util.List;

import duke.interfaces.Command;
import duke.interfaces.Model;
import duke.interfaces.View;
import duke.model.Task;

/**
 * A command to find all tasks in the task list whose description contains a substring.
 */

public class FindTaskCommand implements Command {
    private final Model taskModel;
    private final View taskView;
    private final String searchStr;

    /**
     * Instantiates a command that displays all tasks containing the search string to the view upon execution.
     * @param taskModel The model containing the task list.
     * @param taskView The current view.
     * @param searchStr The string to search.
     */
    FindTaskCommand(Model taskModel, View taskView, String searchStr) {
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
        taskView.setTasks(matchingTasks, true);
    }
}
