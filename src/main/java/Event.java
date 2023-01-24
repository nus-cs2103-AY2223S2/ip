/**
 * Represents an Event, which is a type of Task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    protected String startDatetime;
    protected String endDatetime;

    public Event(String description) {
        super(description.split("/from")[0]);
        String datetimes = description.split("/from")[1];
        this.startDatetime = datetimes.split("/to")[0];
        this.endDatetime = datetimes.split("/to")[1];
    }
}
