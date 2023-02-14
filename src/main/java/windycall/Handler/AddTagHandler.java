package windycall.Handler;

import javafx.util.Pair;
import windycall.parser.Parser;
import windycall.storage.Storage;
import windycall.task.Task;

import java.util.List;

public class AddTagHandler extends OperationHandler {

    @Override
    public void handle(String userCommand) {

    }

    public static String handleAddTag(Parser parser, List<Task> tasks, String[] parts, Storage storage) {
        // valid format: tag #index #tag
        Pair<Integer, String> info = parser.getTagIndex(parts);
        int num = info.getKey();
        String tag = info.getValue();
//        assert num >= 1 && num <= tasks.size();
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
