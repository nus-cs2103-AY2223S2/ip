package tasks;

import static tasks.Priority.LOW;
import static tasks.TaskType.EVENT;

/**
 * Represents a scheduled event
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Creates a new Event object with the given description, starting time and end time
     * with a default priority of LOW
     *
     * @param description the description of the event.
     * @param from the starting time of the event.
     * @param to the end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description, EVENT, LOW);
        this.from = from;
        this.to = to;
    }
    /**
     * Creates a new Event object with the given description, priority, starting time and end time.
     *
     * @param description the description of the event.
     * @param from the starting time of the event.
     * @param to the end time of the event.
     * @param priority the priority of the event.
     */
    public Event(String description, String from, String to, Priority priority) {
        super(description, EVENT, priority);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a new Event object with the given description, completion status,
     * priority and starting time and end time.
     *
     * @param description the description of the event.
     * @param isDone whether the event is completed.
     * @param from the starting time of the event.
     * @param to the end time of the event.
     * @param priority the priority of the event.
     */
    public Event(String description, boolean isDone, String from, String to, Priority priority) {
        super(description, isDone, EVENT, priority);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + type.getValue() + "]"
                + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String parseToSave() {
        return type.getValue() + " | " + super.parseToSave() + " | " + from + " | " + to;
    }
}
