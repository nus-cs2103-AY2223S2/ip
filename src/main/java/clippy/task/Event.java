package clippy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event with a start and end date.
 *
 * @author chunzkok
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Creates an Event instance.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event.
     * @return A string representation of the Event.
     */
    @Override
    public String toString() {
        // only show day of the week + day of the month + month
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("EEE dd MMM")),
                this.to.format(DateTimeFormatter.ofPattern("EEE dd MMM")));
    }

    /**
     * Returns a string representation of the Event in CSV form.
     * @return A string representation of the Event in CSV form.
     */
    @Override
    public String getCsvString() {
        return String.format("E,%s,%s,%s", super.getCsvString(), this.from, this.to);
    }
}
