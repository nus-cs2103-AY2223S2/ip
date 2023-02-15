package task;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import errors.DukeInsufficientArgumentException;
import errors.DukeInvalidCommandException;
import errors.DukeRuntimeException;
import ui.Format;
import ui.Response;
import utils.Utility;

/**
 * A Task with added start and end details
 * @author Nicholas Lee
 */
public class Event extends Task {

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Creates a new Event object with the given details and start/end time.
     *
     * @param details a string describing the event
     * @param start the LocalDateTime object representing the start of the event
     * @param end the LocalDateTime object representing the end of the event
     */
    public Event(String details, LocalDateTime start, LocalDateTime end) {
        super(details);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a new Event object with the given details and start/end time.
     *
     * @param details a string describing the event
     * @param start the LocalDateTime object representing the start of the event
     * @param end the LocalDateTime object representing the end of the event
     * @param isCompleted a boolean indicating whether the event has been completed
     */
    public Event(String details, LocalDateTime start, LocalDateTime end, boolean isCompleted) {
        super(details);
        this.start = start;
        this.end = end;
        super.changeStatus(isCompleted);
    }

    /**
     * Returns the value of the private field 'start' representing the event start date as string for storage
     *
     * @return The current value of 'start' formatted as a string.
     */
    public String getStartString() {
        return Utility.convertDateTimeToString(this.start);
    }

    /**
     * Returns the value of the private field 'end' representing the event end date as string for storage
     *
     * @return The current value of 'end' formatted as a string.
     */
    public String getEndString() {
        return Utility.convertDateTimeToString(this.end);
    }


    public LocalDateTime getStartTime() {
        return this.start;
    }


    public LocalDateTime getEndTime() {
        return this.end;
    }

    /**
     * Prints out a formatted string containing the specific event details
     */
    @Override
    public String toString() {
        return ("EVENT: " + super.getDetails() + " (From " + Utility.getDateTimeString(start)
                + " to " + Utility.getDateTimeString(end) + ")"
                + Format.getCompletionDisplay(super.isCompleted()));
    }

    /**
     * Returns parsed information specific to an "event" command
     *
     * Keys: "details" (the name of the event), "from" (event start date), "to" (event end date)
     * Throws a DukeInsufficientArgumentException if the input is unable to be parsed correctly
     *
     * @param input An event user command
     * @return A hashmap with keys "details", "from" and "to"
     * @exception DukeInsufficientArgumentException if the user did not include any details with the "event" command
     * @exception DukeInvalidCommandException If the event details are out of order
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
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw new DukeInvalidCommandException(Response.INVALID_COMMAND.toString());
        }

        if (detailsSublist.isEmpty() || fromSublist.isEmpty() || toSublist.isEmpty()) {
            throw new DukeInsufficientArgumentException(Response.MISSING_EVENT_DETAILS.toString());
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
