package windycall.Handler;

import javafx.util.Pair;
import windycall.parser.Parser;
import windycall.storage.Storage;
import windycall.task.Task;

import java.util.List;

public class DeleteOperationHandler extends OperationHandler {

    @Override
    public String handle(String userCommand) {
        return "";
    }

    public String handle(Parser parser, List<Task> tasks, String[] parts, Storage storage) {
        Pair<Integer, String> info = parser.getDeleteIndex(parts);
        int num = info.getKey();
        String message = info.getValue();
        assert num >= 1 && num <= tasks.size();
        if (num >= 1 && num <= tasks.size()) {
            String returnedMessage = "Noted. I've removed this task:\n";
            returnedMessage += tasks.get(num - 1) + "\n";
            tasks.remove(num - 1);
            returnedMessage += "Now you have " + tasks.size() + " tasks in the list.";
            storage.handleTaskChange(tasks);
            return returnedMessage;
        } else if (num > tasks.size() || (num < 1 && num != -1)) {
            return "Sorry, your index is out of range";
        } else {
            return message;
        }
    }
}
