package Duke.Tasks;

import Duke.Tasks.Task;

/**
 * Class representing an Event task with a start and end time.
 * @author Bryan Juniano
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructor for ToDo task.
     * @param name  name of the Event task.
     * @param start start time of the Event task.
     * @param end end time of the Event task.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Generates the string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " from: " + this.start + " to: " + this.end + "\n" + this.getTags();
    }
}
