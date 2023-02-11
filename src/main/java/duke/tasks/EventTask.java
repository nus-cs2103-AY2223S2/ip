package duke.tasks;

/**
 * The {@code EventTask} class for tasks with start and end times.
 */
public class EventTask extends Task {

    private final String from;
    private final String to;

    /**
     * Instantiates a new {@code EventTask} object.
     *
     * @param name The name of the task.
     * @param from The start time of the task.
     * @param to   The end time of the task.
     */
    public EventTask(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Copy constructor for the {@code EventTask} class.
     *
     * @param other The {@code EventTask} to be deep copied.
     */
    public EventTask(EventTask other) {
        super(other);
        from = other.from;
        to = other.to;
    }

    public EventTask deepClone() {
        return new EventTask(this);
    }

    public String toDukeFileString() {
        return "E|" + super.toDukeFileString() + "|" + from + "|" + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
