package yj;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, dateTimeFormatter);
        this.to = LocalDateTime.parse(to, dateTimeFormatter);
    }

    /**
     * Returns the formatted start time of the event.
     *
     * @return start time of the event formatted as MMM dd yyyy h:mma.
     */
    public String getFrom() {
        return outputFormatter.format(from);
    }

    /**
     * Sets the start time of the event and saves as a LocalDateTime.
     *
     * @param from start time of the event in the format d/M/yyyy HHmm.
     */
    public void setFrom(String from) {
        this.from = LocalDateTime.parse(from, dateTimeFormatter);
    }

    /**
     * Returns the formatted end time of the event.
     *
     * @return end time of the event formatted as MMM dd yyyy h:mma.
     */
    public String getTo() {
        return outputFormatter.format(to);
    }

    /**
     * Sets the end time of the event and saves as a LocalDateTime.
     *
     * @param to end time of the event in the format d/M/yyyy HHmm.
     */
    public void setTo(String to) {
        this.to = LocalDateTime.parse(to, dateTimeFormatter);
    }

    @Override
    public String toString() {
        return "[E]" + " " + super.getStatusIcon() + " " + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }

    /**
     * Returns the string representation of the event to be saved in the file.
     *
     * @return string representation of the event to be saved in the file.
     */
    @Override
    public String toSaveString() {
        // E | 0 | project meeting | Aug 6th 2-4pm
        return "E | " + (isDone ? 1 : 0) + " | " + super.toString() + " | " + from.format(dateTimeFormatter) + " | " + to.format(dateTimeFormatter);
    }
}
