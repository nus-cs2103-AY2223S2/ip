package duke.task;

/**
 * Represents an event which has to from and to period.
 */
public class Event extends Task {
    private String startDate;
    private String endDate;

    /**
     * Creates an instance of Event with the start and end dates.
     *
     * @param description Describes the event.
     * @param startDate Event starts at this time.
     * @param endDate Event ends at this time.
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns a String representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.startDate, this.endDate);
    }
}
