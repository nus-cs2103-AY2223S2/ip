package duke;

/**
 * Class representing an Event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Parameterized constructor to create an Event
     * @param description description of the task to be created
     * @param from the date/time the Event starts from
     * @param to the date/time the Event ends at
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Parameterized constructor to create an Event
     * @param description description of the task to be created
     * @param isDone if the task has been completed
     * @param from the date/time the Event starts from
     * @param to the date/time the Event ends at
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the String representation of an Event which can be displayed to the user
     * @return the String representation of an Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns the String representation of an Event which can be written to a file
     * @return the String to be written to a file
     */
    @Override
    public String toFile() {
        return "E " + super.toFile() + " /from: " + this.from + " /to: " + this.to;
    }
}
