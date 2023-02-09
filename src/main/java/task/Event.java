package task;

/**
 * Represents an event  with a start and end date and time
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Creates an Event with the given text description, start and end date
     * @param text
     * @param start
     * @param end
     */
    public Event(String text, String start, String end) {
        super(text);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + start + " to:" + end + ")";
    }
}
