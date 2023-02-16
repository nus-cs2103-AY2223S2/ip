package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates a new event task.
 *
 * @author Evan Lee
 * @version CS2103 AY22/23 Semester 2
 */
public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * A public constructor to initialize Event instance.
     *
     * @param task Task name.
     * @param startTime Task start time.
     * @param endTime Task end time.
     */
    public Event(String task, String startTime, String endTime) {
        super(task);
        this.startTime = LocalDate.parse(startTime);;
        this.endTime = LocalDate.parse(endTime);;
    }

    /**
     * Returns the description of Event.
     *
     * @return Event description.
     */
    @Override
    public String getDescription() {
        return "event " + super.getTaskName() + "/from " + this.startTime + " /to " + this.endTime;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
            + "(from: " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
