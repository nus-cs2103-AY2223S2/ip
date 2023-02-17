package duke.tasks;

/**
 * Represents an event
 */
public class Event extends Task {
    private String from;

    private String to;

    /**
     * Represents Event constructor
     * @param description task name
     * @param from starting time for the task
     * @param to deadline for the task
     * @param isDone check whether marks as done
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Overrides toString method
     * @return formatted message
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (" + "from: " + this.from + " " + "to: " + this.to + ")";
    }

    /**
     * Formats an event into a suitable String
     * @return formatted message
     */
    @Override
    public String file() {
        return "E | " + super.file() + " | " + this.from + " | " + this.to;
    }
}
