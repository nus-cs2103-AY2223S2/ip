package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Class for Event task
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor for Event task
     * @param name Name of Event
     * @param startTime Start time of event
     * @param endTime End time of event
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Converts event task to its task log format to be saved in task log file
     * @return String representing event task in task log format
     */
    @Override
    public String toLog() {
        return "E" + super.toLog() + " | "
                + this.startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + " - "
                + this.endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + "\n";
    }

    /**
     * Converts event task to string format which is echoed to user
     * @return String representation of event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.startTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
                + " to: "
                + this.endTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
                + ")";
    }
}
