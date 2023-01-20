package task;

import errors.DukeInsufficientArgumentException;
import errors.DukeInvalidCommandException;
import errors.DukeRuntimeException;
import formatters.Format;
import formatters.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/**
 * A Task with added start and end details
 * @author Nicholas Lee
 */

public class Event extends Task{

    private final String start;
    private final String end;

    public Event(String details, String start, String end) {
        super(details);
        this.start = start;
        this.end = end;
    }

    /**
     * Prints out a formatted string containing the specific event details
     */
    @Override
    public String toString() {
        return ("EVENT: " + super.getDetails() + " (From " + start +" to " + end + ")" +
                Format.getCompletionDisplay(super.isCompleted()));
    }

    /**
     * Returns a hashmap containing parsed information specific to an "event" command
     *
     * Keys: "details" (the name of the event), "from" (event start date), "to" (event end date)
     * Throws a DukeInsufficientArgumentException if the input is unable to be parsed correctly
     *
     * @param input An event user command
     * @exception DukeInsufficientArgumentException If the event details are out of order
     * or the user did not include any details with the "event" command
     */
    public static HashMap<String, String> parse(String input) throws DukeRuntimeException {

        List<String> segments = Arrays.asList(input.split(" "));
        List<String> detailsSublist;
        List<String> fromSublist;
        List<String> toSublist;
        int detailsIndex = segments.indexOf("event") + 1;
        int fromIndex = segments.indexOf("/from") + 1;
        int toIndex = segments.indexOf("/to") + 1;


        if (segments.size() <= 1 || detailsIndex == 0 || fromIndex == 0 || toIndex == 0) {
            throw new DukeInsufficientArgumentException(Response.MISSING_EVENT_DETAILS.toString());
        }

        try {
            detailsSublist = segments.subList(detailsIndex, fromIndex - 1);
            fromSublist = segments.subList(fromIndex, toIndex - 1);
            toSublist = segments.subList(toIndex, segments.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.INVALID_COMMAND.toString());
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
