package windycall;

import java.util.List;

public class FindOperationHandler extends OperationHandler{

    @Override
    public void handle(String userCommand) {

    }

    public static void handle(String[] parts, Ui ui, List<Task> tasks, String userCommand) {
        if (parts.length == 1) {
            Ui.space();
            System.out.println("Please input description of task you want to find");
        } else {
            ui.displayTasks(tasks, userCommand.substring(5));
        }
    }
}
