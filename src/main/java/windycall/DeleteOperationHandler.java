package windycall;

import javafx.util.Pair;

import java.util.List;

public class DeleteOperationHandler extends OperationHandler {

    @Override
    public void handle(String userCommand) {

    }

    public static String handle(Parser parser, List<Task> tasks, String[] parts, Storage storage) {
        Pair<Integer, String> info = parser.getMarkIndex(parts);
        int num = info.getKey();
        String message = info.getValue();
        if (num >= 1 && num <= tasks.size()) {
            String returnedMessage = "Noted. I've removed this task:\n";
            returnedMessage += tasks.get(num - 1) + "\n";
            tasks.remove(num - 1);
            returnedMessage += "Now you have " + tasks.size() + " tasks in the list.";
            storage.handleTaskChange(tasks);
            return returnedMessage;
        } else if (num > tasks.size()) {
            return "Sorry, your index is out of range";
        } else {
            return message;
        }
    }
}
