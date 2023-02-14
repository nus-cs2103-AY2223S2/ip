package duke.tasks;

import java.time.format.DateTimeParseException;

/**
 * Represents an Event task, encapsulating a start and end times as Strings.
 */
public class Event extends Task {

    private final String start;
    private final String end;

    /**
     * Constructor for an Event task.
     * @param description The name of the Event.
     * @param start The start time as a String.
     * @param end The end time as a String.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

<<<<<<< HEAD
=======
    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

>>>>>>> branch-A-codingStandard
    /**
     * String representation of an Event, in the format <code>[E] description (from: start to end)</code>.
     * @return String representation of Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}

