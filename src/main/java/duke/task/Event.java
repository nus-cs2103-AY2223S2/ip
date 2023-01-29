package duke.task;

/**
 * Class for Event object.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */

public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for Event.
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Message printed when a new Event task is added.
     *
     * @return String representing the Event task information.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}


