/**
 * Class that defines the Event type of tasks
 */
public class Event extends Task {

    /** Start date for this event */
    protected String fromDate;
    /** End date for this event */
    protected String toDate;

    /**
     * Constructor for objects of type Event
     *
     * @param userInput specifies the fromDate, toDate and title of an Event object
     */
    public Event(String userInput) {
        super(userInput.substring(6, userInput.indexOf("/from ") - 1));
        this.fromDate = userInput.substring(userInput.indexOf("/from ") + 6, userInput.indexOf("/to ") - 1);
        this.toDate = userInput.substring(userInput.indexOf("/to ") + 4);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }
}
