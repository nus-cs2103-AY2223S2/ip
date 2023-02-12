package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event task.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Class constructor.
     * @param description Name of the Event task.
     * @param from From date for the Event task.
     * @param to To date for the Event task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Print the Event task in desired format.
     * @return Event task printed in format "[E][] task name (from: yyyy/MM/dd HH:mm to: yyyy/MM/dd HH:mm)".
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return "[E]" + super.getStatusIcon() + " (from: " + dateFormat.format(this.from) + " to: "
                + dateFormat.format(this.to) + ")";
    }

    /**
     * Save the Event task in desired format.
     * @return Event task saved in text file in format "E| |task name |yyyy/MM/dd HH:mm|yyyy/MM/dd HH:mm".
     */
    @Override
    public String toRecord() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return "E|" + super.toRecord() + "|" + dateFormat.format(from) + "|" + dateFormat.format(to);
    }
}
