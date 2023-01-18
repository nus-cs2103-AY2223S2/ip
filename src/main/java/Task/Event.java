package Task;

public class Event extends Task {
    final private String from;
    final private String to;

    /**
     * Constructor to create a new event
     * @param task Event task
     * @param from start time of the event
     * @param to end time of the event
     */
    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String statusIcon = this.completed ? "X" : " ";
        return "[E][" + statusIcon + "] " + this.task + " (from: " + this.from + " to: " + this.to +")";
    }
}
