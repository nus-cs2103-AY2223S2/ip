package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Class constructor.
     *
     * @param description description of event task
     * @param from date/time the event starts
     * @param to date/time the event ends
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
    }

    /**
     * Class constructor to be used when reading from files.
     *
     * @param description description of event task
     * @param from date/time the event starts
     * @param to date/time the event ends
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of an event.
     * @return the string representation of an event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("HH:mm, EEEE, MMM dd yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("HH:mm, EEEE, MMM dd yyyy")) + ")";
    }
}
