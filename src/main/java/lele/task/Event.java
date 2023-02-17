package lele.task;

/**
 * A task to be completed, set with a duration.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Instantiates parent Task, the date and starting time,
     * and the time due.
     *
     * @param description Description of Event.
     * @param from The time and date from.
     * @param to The time due.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Identifier of the type of task for storage.
     *
     * @return The type of task in a string.
     */
    @Override
    public String getName() {
        return "E";
    }

    /**
     * The starting date and time of the event.
     *
     * @return String representation of the starting date and time set.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * The ending time of the event.
     *
     * @return String representation of the ending time.
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Prepends the task type, and the duration set at
     * the back.
     *
     * @return The string representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
