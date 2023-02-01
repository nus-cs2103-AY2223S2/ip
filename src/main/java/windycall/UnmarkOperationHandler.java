package windycall;

import java.util.List;

public class UnmarkOperationHandler extends OperationHandler {
    @Override
    public void handle(String userCommand) {

    }

    public static void handle(Parser parser, List<Task> tasks, String[] parts, Storage storage) {
        int idx = parser.getUnmarkIndex(parts);
        if (idx >= 1 && idx <= tasks.size()) {
            System.out.println("     OK, I've marked this task as not done yet:");
            tasks.get(idx - 1).unmark();
            Ui.space();
            System.out.println(tasks.get(idx - 1));
            storage.handleTaskChange(tasks);
        } else if (idx != -1) {
            System.out.println("     Sorry, your index is out of range");
        }
    }
}
