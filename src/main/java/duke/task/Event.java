package duke.task;

/**
 * Subclass of tasks representing an event task
 */
public class Event extends Task {
    private static final String TYPE = "E";
    private String startDateTime;
    private String dueDateTime;

    /**
     * Constructor for creating an Event task stored in the storage
     *
     * @param description task's desc
     * @param from starting date and time of event
     * @param to ending date and time of event
     * @param isDone whether the task is done
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone, TYPE);

        this.startDateTime = from;
        this.dueDateTime = to;
    }

    /**
     * Constructor for creating a new Event task
     *
     * @param description task's desc
     * @param from starting date and time of event
     * @param to ending date and time of event
     */
    public Event(String description, String from, String to) {
        super(description, false, TYPE);

        startDateTime = from;
        dueDateTime = to;
    }

    /**
     * Returns the string representation of an event task
     *
     * @return string representation of an event task
     */
    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                startDateTime, dueDateTime);
    }
}
