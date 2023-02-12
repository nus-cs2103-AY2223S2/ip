package panav.task;

/**
 * Class to represent an Event. It contains attributes for its start and end timelines.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public static final String COMMAND_WORD = "event";

    /**
     * Constructor to initialise attributes.
     * @param description event description.
     * @param from start time.
     * @param to end time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns command word of task.
     * @return command word.
     */
    @Override
    public String getCommand() {
        return Event.COMMAND_WORD;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
