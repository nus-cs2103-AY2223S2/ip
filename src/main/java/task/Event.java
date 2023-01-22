package task;

/**
 * A task with a starting and ending date and time.
 */
public class Event extends Task {

    /** Starting date and time of event */
    protected String from;

    /** Ending date and time of event */
    protected String to;

    /**
     * Constructs a new event.
     *
     * @param description Description of the event.
     * @param from Starting date and time of the event.
     * @param to Ending date and time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getRecreateCommand(int id) {
        String result = "event " + description + " /from "
                + from + " /to " + to;
        if (isDone) {
            result += "\nmark " + id;
        }
        return result;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
