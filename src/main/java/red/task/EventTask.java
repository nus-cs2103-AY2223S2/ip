package red.task;

/**
 * This class is for tasks that are considered an event with a timeframe.
 */
public class EventTask extends Task {

    protected String by;
    protected String byy;

    /**
     * Constructor for a EventTask that takes in a description as well as starting and ending time.
     *
     * @param description The description of the EventTask.
     * @param by The starting time of the event.
     * @param byy The ending time of the event.
     */
    public EventTask(String description, String by, String byy) {
        super(description);
        this.by = by;
        this.byy = byy;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + by + " To: " + byy + ")";
    }
}