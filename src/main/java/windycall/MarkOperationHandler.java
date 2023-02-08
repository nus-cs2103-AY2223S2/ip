package windycall;

import java.util.List;
import javafx.util.Pair;

public class MarkOperationHandler extends OperationHandler {

    @Override
    public void handle(String userCommand) {

    }

    public static String handle(Parser parser, List<Task> tasks, String[] parts, Storage storage) {
//        int num = parser.getMarkIndex(parts);
        Pair<Integer, String> info = parser.getMarkIndex(parts);
        int num = info.getKey();
        String message = info.getValue();
        assert num >= 1 && num <= tasks.size();
        if (num >= 1 && num <= tasks.size()) {
            tasks.get(num - 1).markAsDone();
            String returnedMessage = "Good job! I've marked this task as done:\n";
            returnedMessage += tasks.get(num - 1);
            storage.handleTaskChange(tasks);
            return returnedMessage;
        } else if (num > tasks.size()) {
            return "Sorry, your index is out of range";
        } else {
            return message;
        }
    }


}
