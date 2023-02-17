package duke.task;

import java.util.ArrayList;

/**
 * A task subclass to represent an Event (start and end dates).
 */
public class Event extends Task {
    // Start and end dates/time.
    private String startDate;
    private String endDate;

    /**
     * Constructor for Task class.
     * @param description The task details.
     * @param startDate The event start date/time.
     * @param endDate The event end date/time.
     * @param tags Tags for the task.
     */
    public Event(String description, String startDate, String endDate, ArrayList<String> tags) {
        super(description, tags);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Event) {
            Event other = (Event) obj;
            return this.description.equals(other.description)
                    && this.startDate.equals(other.startDate)
                    && this.endDate.equals(other.endDate);
        }
        return false;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String getDescription() {
        return this.description + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
