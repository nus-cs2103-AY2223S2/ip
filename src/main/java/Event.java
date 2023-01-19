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


    public static HashMap<String, String> parseEvent(String input) throws IllegalArgumentException {

        List<String> segments = Arrays.asList(input.split(" "));

        if (segments.size() <= 1) {
            throw new IllegalArgumentException("Command must contain additional information");
        }

        int detailsIndex = segments.indexOf("event") + 1;
        int fromIndex = segments.indexOf("/from") + 1;
        int toIndex = segments.indexOf("/to") + 1;

        List<String> detailsSublist = segments.subList(detailsIndex, fromIndex - 1);
        List<String> fromSublist = segments.subList(fromIndex, toIndex - 1);
        List<String> toSublist = segments.subList(toIndex, segments.size());


        if (detailsSublist.size() == 0 || fromSublist.size() == 0 || toSublist.size() == 0) {
            throw new IllegalArgumentException("Command must contain additional information, missing event '/from' or '/to' or event details");
        }

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
