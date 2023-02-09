package duke;

/**
 * Encapsulates a Task with a specific start and end date/time.
 *
 * @author Sean Chin Jun Kai
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructor for creating an Event object.
     *
     * @param description Name of the Task.
     * @param start Start date/time of this Task.
     * @param end End date/time of this Task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns string representation of an Event object which users can see in the GUI.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(from: " + this.start + "to: " + this.end + ")\n";
    }

    /**
     * Returns string representation of an Event object to store in txt file.
     *
     * @return String representation of Event.
     */
    @Override
    public String getText() {
        return "E " + super.getText()
                + " | " + start + " | " + end;
    }
}
