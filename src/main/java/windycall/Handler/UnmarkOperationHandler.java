package windycall.Handler;

import javafx.util.Pair;
import windycall.parser.Parser;
import windycall.storage.Storage;
import windycall.task.Task;

import java.util.List;

public class UnmarkOperationHandler extends OperationHandler {
    @Override
    public void handle(String userCommand) {

    }

    public static String handle(Parser parser, List<Task> tasks, String[] parts, Storage storage) {
//        int idx = parser.getUnmarkIndex(parts);
        Pair<Integer, String> info = parser.getUnmarkIndex(parts);
        int num = info.getKey();
        String message = info.getValue();
        assert num >= 1 && num <= tasks.size();
        if (num >= 1 && num <= tasks.size()) {
            tasks.get(num - 1).unmark();
            String returnedMessage = "Good job! I've unmarked this task as not done yet:\n";
            returnedMessage += tasks.get(num - 1);
            storage.handleTaskChange(tasks);
            return returnedMessage;
        } else if (num > tasks.size() || (num < 1 && num != -1)) {
            return "Sorry, your index is out of range";
        } else {
            return message;
        }
    }
}
