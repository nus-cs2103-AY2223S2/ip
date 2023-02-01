package windycall;

import java.util.List;

public class MarkOperationHandler extends OperationHandler {

    @Override
    public void handle(String userCommand) {

    }

    public static void handle(Parser parser, List<Task> tasks, String[] parts, Storage storage) {
        int num = parser.getMarkIndex(parts);
        if (num >= 1 && num <= tasks.size()) {
            System.out.println("     Good job! I've marked this task as done:");
            tasks.get(num - 1).markAsDone();
            Ui.space();
            System.out.println(tasks.get(num - 1));
            storage.handleTaskChange(tasks);
        } else if (num != -1) {
            System.out.println("     Sorry, your index is out of range");
        }
    }


}
