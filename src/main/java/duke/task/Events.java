package duke.task;

import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class of events that encapsulates the event object created by event command
 */
public class Events extends Task {

    protected LocalDateTime startTime;

    protected LocalDateTime endTime;

    /**
     * A constructor of events object
     *
     * @param description the task to be stored
     * @param startTime   the time the task starts
     * @param endTime     the time the task ends
     */
    public Events(String description, String startTime, String endTime) {
        super(description);
        DateTimeFormatter formatterStart = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm ");
        this.startTime = LocalDateTime.parse(startTime, formatterStart);
        DateTimeFormatter formatterEnd = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.endTime = LocalDateTime.parse(endTime, formatterEnd);
    }

    /**
     * A to string method of format [E][] (from: MMM d yyyy HHmm to: MMM d yyyy HHmm)
     *
     * @return the string of above specifications
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) +
                " to: " + endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}