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

    /**
     * Method to get that just returns the String representation of the tasks
     * containing just that part on which the 'find' command can search.
     * For eg. a find command should not be able to search "find from" and then
     * all the events are displayed. It should only be able to search the description and
     * timeframes.
     * @return searchable part of the string
     */
    @Override
    public String findablePart() {
        String findablePart = this.description + this.from + this.to;
        return findablePart;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
