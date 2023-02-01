package windycall;

import java.util.List;

public class DeleteOperationHandler extends OperationHandler{

    @Override
    public void handle(String userCommand) {

    }

    public static void handle(Parser parser, List<Task> tasks, String[] parts, Storage storage) {
        int idx1 = parser.getDeleteIndex(parts);
        if (idx1 >= 1 && idx1 <= tasks.size()) {
            System.out.println("     Noted. I've removed this task:");
            Ui.space();
            System.out.println(tasks.get(idx1 - 1));
            tasks.remove(idx1 - 1);
            storage.handleTaskChange(tasks);
            Ui.space();
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else if (idx1 != -1) {
            System.out.println("     Sorry, your index is out of range");
        }
    }
}
