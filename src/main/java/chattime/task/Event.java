package chattime.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents task object of 'event' type.
 */
public class Event extends Task {

    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;

    /**
     * Creates Event object with parent constructor and parsed description.
     *
     * @param description Event task name.
     * @param fromDate Start date description of task.
     * @param fromTime Start time description of task.
     * @param toDate Deadline date description of task.
     * @param toTime Deadline time description of task.
     */
    public Event(String description, LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Generates a data string of event task to be stored in storage file.
     *
     * @return Data string of event task.
     */
    @Override
    public String toDataString() {
        return "E" + super.toDataString() + " @ " + this.fromDate + " @ " + this.fromTime
                + " @ " + this.toDate + " @ " + this.toTime;
    }

    /**
     * Returns comparison result of input time with task relevant time.
     *
     * @param time User's input time.
     * @return true if the input time is between the task start time and the task deadline, otherwise false.
     */
    @Override
    public boolean onDate(LocalDate time) {
        return this.fromDate.isEqual(time) || this.toDate.isEqual(time) ||
                (this.fromDate.isBefore(time) && this.toDate.isAfter(time));
    }

    /**
     * Returns current data of event task.
     *
     * @return Current situation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy ")) +
                (this.fromTime == null ? "" : this.fromTime.format(DateTimeFormatter.ofPattern("hh:mm a "))) +
                "to: " + this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy ")) +
                (this.toTime == null ? "" : this.toTime.format(DateTimeFormatter.ofPattern("hh:mm a"))) + ")";
    }
}
