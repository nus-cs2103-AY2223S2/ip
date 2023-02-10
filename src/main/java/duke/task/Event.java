package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class extends the Task class to represent a task with a start time and end time.
 *
 * @author owen-yap
 */
public class Event extends Task {

    protected final LocalDateTime from;
    protected final LocalDateTime to;
    /**
     * Constructs a new Event with the given description, start time and end time.
     *
     * @param description description of the event
     * @param from start time of the event
     * @param to end time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        assert !description.isEmpty();
        assert from != null;
        assert to != null;
    }

    /**
     * Overrides the `toString` method to return the Event task in the form:
     * "[E][taskStatus] taskDescription (from: startTime to: endTime)".
     *
     * @return a string representation of the Event task
     */
    @Override
    public String toString() {
        String fromStr = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
        String toStr = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
        return "[E]" + super.toString() + " (from: " + fromStr + " to: " + toStr + ")";
    }
}
