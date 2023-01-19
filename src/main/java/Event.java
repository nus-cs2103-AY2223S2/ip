/**
 * A task subclass to represent an Event (start and end dates).
 */
public class Event extends Task{
    private String startDate;
    private String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
