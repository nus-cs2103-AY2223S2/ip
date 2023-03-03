package duke;

import java.time.LocalDateTime;

/**
 * Represents an Event entered by the user.
 */
public class Event extends Task {

    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;

    /**
     * Constructor for the Event.
     *
     * @param taskDescription Description of the Event.
     * @param fromTime The Time and Date the deadline is from.
     * @param toTime The Time and Date the deadline is to.
     */
    public Event(String taskDescription, LocalDateTime fromTime, LocalDateTime toTime) {
        setTaskDescription(taskDescription);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s from: %s to: %s", super.toString(),
                fromTime.format(getFormatter()), toTime.format(getFormatter()));
    }
}
