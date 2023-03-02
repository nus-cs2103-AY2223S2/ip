package chad.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event that runs for a duration.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final String formattedStart;
    private final String formattedEnd;

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
        this.formattedStart = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
        this.formattedEnd = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }

    @Override
    public String getDescription() {
        return this.task + " (from: " + formattedStart + " to: " + formattedEnd + ")";
    }

    @Override
    public String toData() {
        String[] fromDate = this.from.toString().split("T");
        String fromDateData = fromDate[0] + " " + fromDate[1].replace(":", "");
        String[] toDate = this.to.toString().split("T");
        String toDateData = toDate[0] + " " + toDate[1].replace(":", "");
        String status = this.isCompleted ? "1" : "0";

        return "E | " + status + " | " + this.task + " | " + fromDateData + " | " + toDateData;
    }

    @Override
    public String toString() {
        String statusIcon = this.isCompleted ? "X" : " ";

        return "[E][" + statusIcon + "] " + this.getDescription();
    }
}
