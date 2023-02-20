package windycall.handler;

import java.util.List;

import windycall.task.Task;
import windycall.ui.Ui;

public class ListOperationHandler extends OperationHandler {

    @Override
    public String handle(String userCommand) {
        return "";
    }

    public String handle(List<Task> tasks, Ui ui) {
        return ui.displayTasks(tasks);
    }
}
