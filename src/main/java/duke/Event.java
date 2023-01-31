package duke;

/**
 * Models an event which is a task
 */
public class Event extends Task {

    /** String used to represent the start time of event. */
    protected String from;
    /** String used to represent the end time of event. */
    protected String to;
    /** String used to assign the name of the event. */
    protected String desc;

    /**
     * Constructor for the Event class.
     *
     * @param desc The name of the task.
     * @param from The starting time of event.
     * @param to The ending time of event.
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    /**
     * Overloaded constructor for the Event class.
     *
     * @param desc The name of the task.
     * @param from The starting time of event.
     * @param to The ending time of event.
     * @param b The status of the task.
     */
    public Event(String desc, String from, String to, boolean b) {
        super(desc, b);
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * {@inheritDoc}
     */
    public String asCsv() {
        if (super.isDone) {
            return "E,1," + desc + "," + from + "," + to;
        } else {
            return "E,0," + desc + "," + from + "," + to;
        }
    }
}
