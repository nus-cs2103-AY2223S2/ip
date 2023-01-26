package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor for an event.
     * @param description Name of event
     * @param start Time of event start
     * @param end Time of event end
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    /**
     * Constructor for an event.
     * @param description Name of event
     * @param isCompleted Whether it is completed
     * @param start Time of event start
     * @param end Time of event end
     */
    public Event(String description, boolean isCompleted, LocalDate start, LocalDate end) {
        super(description, isCompleted);
        this.start = start;
        this.end = end;
    }
    /**
     * Getter for the start time.
     * @return start time as a date
     */
    public LocalDate getStartTime() {
        return this.start;
    }
    /**
     * Getter for the end time.
     * @return end time as a date
     */
    public LocalDate getEndTime() {
        return this.end;
    }
    @Override
    public String toString() {
        String startFormatted = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endFormatted = this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString()
                + " (from: " + startFormatted + " to: " + endFormatted + ")";
    }
}