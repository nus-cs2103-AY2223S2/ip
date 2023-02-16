package task;
import java.time.LocalDateTime;

import core.DateHandler;
/**
 * Event is a type of Task.
 * Events have a 'from' and 'to' field to indicate the period which the task takes place.
 */

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event task with the given name, status and timing details.
     *
     * @param description The name of this Event
     * @param isComplete The status of this Event
     * @param from The start date time of this Event
     * @param to The end date time of this Event
     */
    public Event(String description, Boolean isComplete, LocalDateTime from, LocalDateTime to) {
        super(description, isComplete);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the String representation of an Event task.
     *
     * @return The name of this task and the timing details.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s , to: %s)", super.toString(), DateHandler.convertForPrint(this.from),
                DateHandler.convertForPrint(this.to));
    }

    /**
     * Returns the String representation of an Event task delimited by commas.
     *
     * @return The name of this task and the timing details in CSV format.
     */
    @Override
    public String toCsv() {
        return String.format("E,%s,%s,%s,%s", this.getTaskDescription(), this.getComplete(),
                DateHandler.convertForSave(this.from), DateHandler.convertForSave(this.to));
    }

}
