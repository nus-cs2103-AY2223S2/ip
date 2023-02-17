package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class to handle all event task
 */
public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Constructor for Event
     * @param description
     * @param startDate
     * @param endDate
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.endDate = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        String formattedStartDate = startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String formattedEndDate = endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[E]" + super.toString() + " (from: " + formattedStartDate + " to: " + formattedEndDate + ")";
    }

    @Override
    public String formatForFile() {
        String formattedStartDate = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        String formattedEndDate = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        if (this.isDone) {
            return "E | " + 1 + " | " + description + " | " + formattedStartDate + " | " + formattedEndDate + "\n";
        } else {
            return "E | " + 0 + " | " + description + " | " + formattedStartDate + " | " + formattedEndDate + "\n";
        }
    }

    @Override
    public Type getType() {
        return Type.EVENT;
    }
}
