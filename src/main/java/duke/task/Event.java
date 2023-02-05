package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event that runs for a duration.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor to create a new event
     * @param task Event task
     * @param from start time of the event
     * @param to end time of the event
     */
    public Event(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toData() {
        String[] fromDate = this.from.toString().split("T");
        String fromDateData = fromDate[0] + " " + fromDate[1].replace(":", "");
        String[] toDate = this.from.toString().split("T");
        String toDateData = toDate[0] + " " + toDate[1].replace(":", "");
        String status = this.completed ? "1" : "0";
        return "E | " + status + " | " + this.task + " | " + fromDateData + " | " + toDateData;
    }

    @Override
    public String toString() {
        String statusIcon = this.completed ? "X" : " ";
        String formattedStart = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
        String formattedEnd = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
        return "[E][" + statusIcon + "] " + this.task + " (from: " + formattedStart + " to: " + formattedEnd + ")";
    }
}
