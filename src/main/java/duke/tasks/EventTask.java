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

    public String toDukeFileString() {
        return "E|" + super.toDukeFileString() + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
