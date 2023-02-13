package duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event task which is to be stored by duke.Utilities.Duke. An Event has an event name, start time and end time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime until;

    /**
     * The constructor for an event object.
     * @param name The event name.
     * @param from The event start time.
     * @param until The event end time.
     */
    public Event(String name, LocalDateTime from, LocalDateTime until) {
        super(name);
        this.from = from;
        this.until = until;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String taskToData() {
        return String.format("[E] | %s | %s | %s | %s", this.getStatusIcon(), this.name, this.from, this.until);
    }


    @Override
    public String toString() {
        return name + " (from: " + this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + " to: " + this.until .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ")";
    }
}
