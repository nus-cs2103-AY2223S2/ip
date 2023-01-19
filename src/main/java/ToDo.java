import errors.InsufficientToDoArgumentException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ToDo extends Task{

    public ToDo(String details) {
        super(details);
    }

    @Override
    public String toString() {
        String completionDisplay;
        if (super.isCompleted()) {
            completionDisplay = "[X]";
        } else {
            completionDisplay = "[ ]";
        }
        return ("TO-DO: " + super.getDetails() + completionDisplay);
    }


    public static HashMap<String, String> parseTodo(String input) throws InsufficientToDoArgumentException {

        List<String> segments = Arrays.asList(input.split(" "));

        if (segments.size() <= 1) {
            throw new InsufficientToDoArgumentException("Command must contain additional information");
        }

        int detailsIndex = segments.indexOf("to-do") + 1;

        List<String> detailsSublist = segments.subList(detailsIndex, segments.size());

        String details = String.join(" ", detailsSublist);

        HashMap<String, String> result = new HashMap<>();
        result.put("details", details);
        return result;
    }
}
