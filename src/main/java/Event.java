/**
 * Event is a type of Task.
 * Events have a 'from' and 'to' field to indicate the period which the task takes place.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructor that takes in the name of the Task alongside with other timing details.
     * @param task_name The name of this Task
     * @param from A string representation of the start date/time of this Task
     * @param to A string representation of the end date/time of this Task
     */
    public Event(String task_name, String from, String to) {
        super(task_name);
        this.from = from;
        this.to = to;
    }
    /**
     * Returns String representation of an Event.
     * @return The name of this task and the from and to details.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s , to: %s)", super.toString(), this.from, this.to);
    }
}
