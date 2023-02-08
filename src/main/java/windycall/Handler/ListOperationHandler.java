package windycall.Handler;
import windycall.task.Task;
import windycall.ui.Ui;

import java.util.List;

public class ListOperationHandler extends OperationHandler {

    @Override
    public void handle(String userCommand) {

    }

    public static String handle(List<Task> tasks, Ui ui) {
        return ui.displayTasks(tasks);
    }
}
