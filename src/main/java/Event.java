import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of Task with a specific window for
 * completion.
 */
public class Event extends Task {
    // Start date for the task.
    protected LocalDateTime from;
    // End date for the task.
    protected LocalDateTime to;

    /**
     * Constructor for the event class.
     *
     * @param description Description of the event task.
     * @param from Start date for the task.
     * @param to End date for the task.
     */
    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        boolean saved = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.to = LocalDateTime.parse(to, formatter);

    }

    /**
     * Overrides the toString method of the Task class.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + ")";
    }

    /**
     * Overrides the printData method of the Task class.
     *
     * @return String representation of the Event task in data form.
     */
    @Override
    public String printData() {
        return "E" + "/" + (isDone ? "1" : "0") + "/" + description
                + "/" + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + "/" + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
