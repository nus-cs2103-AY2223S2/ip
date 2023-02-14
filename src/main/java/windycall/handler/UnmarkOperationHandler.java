package windycall.handler;

import java.util.List;

import javafx.util.Pair;
import windycall.parser.Parser;
import windycall.storage.Storage;
import windycall.task.Task;

public class UnmarkOperationHandler extends OperationHandler {
    @Override
    public String handle(String userCommand) {
        return "";
    }

    public String handle(Parser parser, List<Task> tasks, String[] parts, Storage storage) {
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
