/**
 * Event is a type of Task.
 * @author EL
 */
public class Event extends Task {
    private String from;
    private String to;

    public Event(String task_name, String from, String to) {
        super(task_name);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s , to: %s)", super.toString(), this.from, this.to);
    }
}
