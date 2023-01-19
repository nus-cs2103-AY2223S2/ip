import errors.InsufficientEventArgumentException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Event extends Task{

    String start;
    String end;

    public Event(String details, String start, String end) {
        super(details);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String completionDisplay;
        if (super.isCompleted()) {
            completionDisplay = "[X]";
        } else {
            completionDisplay = "[ ]";
        }
        return ("EVENT: " + super.getDetails() + " (From " + start +" to " + end + ")" + completionDisplay);
    }


    public static HashMap<String, String> parseEvent(String input) throws InsufficientEventArgumentException {

        List<String> segments = Arrays.asList(input.split(" "));

        if (segments.size() <= 1) {
            throw new InsufficientEventArgumentException("Did you forget to add the event details?");
        }

        int detailsIndex = segments.indexOf("event") + 1;
        int fromIndex = segments.indexOf("/from") + 1;
        int toIndex = segments.indexOf("/to") + 1;


        if (detailsIndex == 0 || fromIndex == 0 || toIndex == 0)  {
            throw new InsufficientEventArgumentException("Did you forget to add the event description or '/from' or '/to' details?");
        }

        List<String> detailsSublist = segments.subList(detailsIndex, fromIndex - 1);
        List<String> fromSublist = segments.subList(fromIndex, toIndex - 1);
        List<String> toSublist = segments.subList(toIndex, segments.size());

        String details = String.join(" ", detailsSublist);
        String from = String.join(" ", fromSublist);
        String to = String.join(" ", toSublist);

        HashMap<String, String> result = new HashMap<>();
        result.put("details", details);
        result.put("from", from);
        result.put("to", to);
        return result;
    }
}
