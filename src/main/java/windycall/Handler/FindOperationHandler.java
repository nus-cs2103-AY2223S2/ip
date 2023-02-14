package windycall.Handler;

import windycall.task.Task;
import windycall.ui.Ui;

import java.util.List;

public class FindOperationHandler extends OperationHandler {

    @Override
    public String handle(String userCommand) {
        return "";
    }

    public String handle(String[] parts, Ui ui, List<Task> tasks, String userCommand) {
        if (parts.length == 1) {
            return "Please input description of task you want to find";
        } else {
            return ui.displayTasks(tasks, userCommand.substring(5));
        }
    }
}
