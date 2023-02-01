package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event task class.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event object.
     * @param description Description of task.
     * @param from Start date of task.
     * @param to End date of task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date as string.
     * @return Start date of task.
     */
    public String getFrom() {
        return this.from.toString();
    }

    /**
     * Returns the end date as string.
     * @return End date of task.
     */
    public String getTo() {
        return this.to.toString();
    }

    /**
     * Returns string representation of event.
     * @return String representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
