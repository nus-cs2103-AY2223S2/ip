package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event, with a start time and end time.
 */
public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String title, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(title, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns formatted string representation of the Event.
     * This string includes the task completion status, type and name of task,
     * and the date and time specifying when the event begins and concludes.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        String doneString = this.getDone() ? "X" : " ";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy, HH:mm");
        return String.format("[E][%s] %s (from: %s, to: %s)", doneString, this.getTitle(),
                this.start.format(dateFormat), this.end.format(dateFormat));
    }

    /**
     * Returns formatted string representation of the Event, for storage in memory.
     * This string includes the task completion status, type and name of task,
     * and the date and time specifying when the event begins and concludes.
     *
     * @return String representation of the Event, for storage in memory.
     */
    public String convertToMemoryString() {
        String doneString = this.getDone() ? "1" : "0";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE MMM dd yyyy HH:mm a");
        return "E, " + doneString + ", " + this.getTitle() + ", "
                + this.start.format(dateFormat) + ", " + this.end.format(dateFormat);
    }

}
