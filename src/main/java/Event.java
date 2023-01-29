/**
 * Class Event is a subclass of Task, encapsulate details
 * about a type of user's tasks which happen during a period
 * of time.
 *
 * @author hhchinh2002
 */
public class Event extends Task {
    // The starting time of the Event
    private String start;
    //The ending time of the Event
    private String end;

    /**
     * Creates an Event task object with given description and starting, ending time
     *
     * @param description The description for the Event task
     * @param start The starting time of the Event
     * @param end The ending time fo the Event
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getStartTime() {
        return this.start;
    }

    public String getEndTime() {
        return this.end;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event target = (Event) o;
            return target.getDescription().equals(this.getDescription())
                    && target.getStartTime().equals(this.getStartTime())
                    && target.getEndTime().equals(this.getEndTime());
        }
        return false;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.start + " to: " + this.end + ")";
    }
}
