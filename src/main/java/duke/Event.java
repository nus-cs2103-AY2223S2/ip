package duke;

/**
 * Event class
 */
public class Event extends Task {
    private static final String TASK_TYPE = "[E]";
    private String from;
    private String to;

    /**
     * constructor for an instantiating an event object
     * @param description description of the event
     * @param from start time of the event
     * @param to end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event object
     * @return String string representation of the event object, which includes task type, completion status, from date
     * and to date
     */
    @Override
    public String toString() {
        return TASK_TYPE + super.toString() + " (from: " + this.from + " to: " + this.to + ")"
                + "\nPriority: " + getPriority();
    }

    /**
     * Returns the string representation of the event object to be stored into the data.txt file
     * @return String string representation with dividers to be stored in the data.txt file
     */
    @Override
    public String toStorageData() {
        return TASK_TYPE + DIVIDER + this.getStatusIcon() + DIVIDER + getPriority() + DIVIDER + super.description
                + DIVIDER + this.from + DIVIDER + this.to;
    }
}
