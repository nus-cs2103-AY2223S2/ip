package duke.task;

/**
 * Represents an event with from and to timings.
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Constructs an event object.
     *
     * @param description title of the event.
     * @param from time of event happening time.
     * @param to expected ending time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event() {
        super();
    }

    /**
     * Represents the string written into todo_list.txt.
     *
     * @return A string written into the todo_list.txt.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to " + to + ")";
    }
}
