package peppa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task. Events are given by the user in the format event {desc} /from {startDate} /to {endDate}.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an event task with the specified name, start date and end date.
     *
     * @param name Name/description of the event.
     * @param from Date on which the event starts.
     * @param to Date on which the event ends.
     */
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFormatString() {
        return "E | " + (super.done ? "1" : "0") + " | " + super.name + " | "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to "
                + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
