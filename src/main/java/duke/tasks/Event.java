package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class for event task.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for event task.
     * @param description the description of the task.
     * @param from the start date of the event.
     * @param to the end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        assert from.isBefore(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
