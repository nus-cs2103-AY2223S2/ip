package duke.task;

import java.time.LocalDateTime;

import duke.parser.Parser;

/**
 * An Event is a task that has a starting time and an ending time
 */
public class Event extends Task {
    protected final LocalDateTime from;
    protected final LocalDateTime to;

    /**
     * Constructor
     * @param name a string indicating thename of the duke.task.Event task
     * @param status a String that checks if the duke.task.Event is done or not
     * @param from a string representing the starting time passed in by the user
     * @param to a string representing the ending time passed in by the user
     */
    public Event(String name, int status, String from, String to) {
        super(name, status);
        this.from = Parser.formatDateTime(from);
        this.to = Parser.formatDateTime(to);
    }

    /**
     * Helps with saving the Task status in binary numbers
     * @return 0 is the task is not done, 1 if the task is done.
     */
    private int getStatusNo() {
        if (super.status.equals("[ ]")) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Overrides toString method: displayes type, then status, then name, then start time, then
     * ending time.
     */
    public String toString() {
        return String.format("[E]%s %s (from %s to %s)", status, name,
                Parser.transformDateTime(from), Parser.transformDateTime(to));
    }

    /**
     * Helps with updating local tasks
     * @return a string to be written to the local hard disk
     */
    public String toStoreFormatString() {
        return String.format("E/%s/%d/%s/%s", super.name, this.getStatusNo(),
                Parser.reverseFormatDateTime(from), Parser.reverseFormatDateTime(to)) + super.toStoreFormatString();
    }
}
