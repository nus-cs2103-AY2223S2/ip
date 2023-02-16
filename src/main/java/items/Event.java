package items;

import java.time.LocalDate;

/**
 * Represents an Event task
 * @author clydelhui
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates a new <code>Event</code> with the given description, start date and end date
     * @param description The description of the <code>Event</code>
     * @param startDate The start date of the <code>Event</code>
     * @param endDate The end date of the <code>Event</code>
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description, "E");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates a new <code>Event</code> with the given description, status, start date and end date
     * @param description The description of the <code>Event</code>
     * @param isDone A boolean indicating the status of the <code>Event</code>
     * @param startDate The start date of the <code>Event</code>
     * @param endDate The end date of the <code>Event</code>
     */
    public Event(String description, boolean isDone, LocalDate startDate, LocalDate endDate) {
        super(description, "E", isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String generateStorageForm() {
        return this.getTaskType() + "@" + this.getDescription() + "@" + this.getStatusIcon() + "@"
                + this.startDate + "@" + this.endDate;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]" + "[" + this.getStatusIcon() + "]"
                + this.description + "/" + this.startDate + "/" + this.endDate;
    }
}
