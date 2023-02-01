package windycall;
import java.util.List;

public class ListOperationHandler extends OperationHandler {

    @Override
    public void handle(String userCommand) {

    }

    public static void handle(List<Task> tasks, Ui ui) {
        ui.displayTasks(tasks);
    }
}
