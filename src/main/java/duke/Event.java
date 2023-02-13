package duke;

/**
 * Event class
 */
public class Event extends Task {

    String From;
    String To;

    /**
     * constructor for an instantiating an event object
     * @param description description of the event
     * @param From start time of the event
     * @param To end time of the event
     */
    public Event(String description, String From, String To) {
        super(description);
        this.From = From;
        this.To = To;
    }

    /**
     * Returns the string representation of the event object
     * @return String string representation of the event object, which includes task type, completion status, from date
     * and to date
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.From + " to: " + this.To + ")";

    }
}
