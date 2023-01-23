package Week2.src.main;

/**
 * A subclass that extends Task.
 * Event contains its occuring time (start time and end time)
 */
public class Event extends Task {
    String from;
    String to;

    /**
     * Event constructor
     * @param content Task content
     * @param from Start time of the event
     * @param to End time of the event
     */
    Event(String content, String from, String to) {
        super(content);
        this.from = from;
        this.to = to;
    }

    /**
     * It overrides toString() method to change information to a string format
     * @return String format of the event task data
     */
    @Override
    public String toString() {
        return "[E][" +this.getDone()+ "] " +this.content+ "(from:" +this.from+ " to:" +this.to+ ")";
    }
}
