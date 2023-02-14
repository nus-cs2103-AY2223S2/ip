package windycall.handler;

import java.util.List;

import javafx.util.Pair;
import windycall.parser.Parser;
import windycall.storage.Storage;
import windycall.task.Task;

public class AddTagHandler extends OperationHandler {

    @Override
    public String handle(String userCommand) {
        return "";
    }

    public String handle(Parser parser, List<Task> tasks, String[] parts, Storage storage) {
        // valid format: tag #index #tag
        Pair<Integer, String> info = parser.getTagIndex(parts);
        int num = info.getKey();
        String tag = info.getValue();
        if (num >= 1 && num <= tasks.size()) {
            tasks.get(num - 1).changeTag(tag);
            String returnedMessage = "I've successfully added tag #" + tag + " to this task:\n";
            returnedMessage += tasks.get(num - 1);
            storage.handleTaskChange(tasks);
            return returnedMessage;
        } else if (num > tasks.size() || (num < 1 && num != -1)) {
            return "Sorry, your index is out of range";
        } else {
            return tag;
        }
    }
}
