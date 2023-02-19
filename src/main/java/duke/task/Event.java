package duke.task;
import java.time.LocalDate;

/**
 * Class for Event object.
 *
 * @author Pearl Twe
 * @version CS2103T AY22/23 Semester 2
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Event task
     * @param description task description
     * @param from date started
     * @param to date ended
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Return Event start-date
     * @return start-date
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Return Event end-date
     * @return end-date
     */
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Encode task into String for easier decode when tasks from loading duke.txt
     * @return String format of task
     */
    @Override
    public String encode() {
        return "event"
                + " " + this.isDone
                + " " + this.getPriority()
                + " " + this.description
                + " " + "/from: " + this.from
                + " " + "/to:" + this.to;
    }

    /**
     * Convert task into String for display in taskList
     * @return String of task type, status, task description, start and end dates
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}