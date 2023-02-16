package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents an Event Task.
 * It contains the task description as well as the start and end of
 * the event in LocalDateTime format.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Returns an Event with the given task description with start and end datetime.
     *
     * @param taskDescription
     * @param start
     * @param end
     */
    public Event(String taskDescription, LocalDateTime start, LocalDateTime end) {
        super(taskDescription);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toStorageFormatString() {
        return "E|" + (isDone? "1" : "0") +
                "|" + this.taskDescription +
                "|" + this.start.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) +
                "|" + this.end.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return "[E]" + super.toString() +
                " (from: " + start.format(dateTimeFormatter) +
                " to: " + end.format(dateTimeFormatter) + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Event)) {
            return false;
        }

        Event e = (Event) o;

        return e.taskDescription.equals(this.taskDescription);
    }

}
