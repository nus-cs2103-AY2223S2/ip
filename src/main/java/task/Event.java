package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that is an event that spans between two times.
 * Extends from task.
 */
public class Event extends Task {
    protected DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected String from;
    protected String to;
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;
    protected String displayFrom;
    protected String displayTo;

    /**
     * Constructs an Event object.
     *
     * @param description String to accompany the task describing the task.
     * @param from String representing the start time of the event.
     * @param to String representing the end time of the event.
     * @return Event object.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        fromDate = LocalDateTime.parse(from, format);
        toDate = LocalDateTime.parse(to, format);
        displayFrom = this.fromDate.toString().replace("T", " ");
        displayTo = this.toDate.toString().replace("T", " ");
    }

    /**
     * Constructs an Event object with additional parameter.
     * Used when loading from file.
     *
     * @param isDone boolean to represent if the task is marked as done.
     * @param description String to accompany the task describing the task.
     * @param from String representing the start time of the event.
     * @param to String representing the end time of the event.
     * @return Event object.
     */
    public Event(boolean isDone, String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
        fromDate = LocalDateTime.parse(from, format);
        toDate = LocalDateTime.parse(to, format);
        displayFrom = this.fromDate.toString().replace("T", " ");
        displayTo = this.toDate.toString().replace("T", " ");
    }

    /**
     * Returns string representation of event object.
     *
     * @return string representing of event object,
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.displayFrom + " to: " + this.displayTo + ")";
    }

    /**
     * Returns String that is a command that can be used to create a similar Event object.
     *
     * @return String that is a command that can be used to create a similar Event object.
     */
    @Override
    public String getCommand() {
        return this.isDone
            ? "1 event " + this.description + " /from " + this.from + " /to " + this.to
            : "0 event " + this.description + " /from " + this.from + " /to " + this.to;

    }
}
