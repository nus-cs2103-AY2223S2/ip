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
     * @param frDate Start date description of task.
     * @param frTime Start time description of task.
     * @param tDate Deadline date description of task.
     * @param tTime Deadline time description of task.
     */
    public Event(String description, LocalDate frDate, LocalTime frTime, LocalDate tDate, LocalTime tTime) {
        super(description);

        fromDate = frDate;
        toDate = tDate;
        fromTime = frTime;
        toTime = tTime;
    }

    /**
     * Generates a data string of event task to be stored in storage file.
     *
     * @return Data string of event task.
     */
    @Override
    public String toDataString() {
        return TaskType.EVENT + super.toDataString() + " @ " + fromDate + " @ " + fromTime
                + " @ " + toDate + " @ " + toTime;
    }

    /**
     * Returns comparison result of input time with task relevant time.
     *
     * @param time User's input time.
     * @return true if the input time is between the task start time and the task deadline, otherwise false.
     */
    @Override
    public boolean isOnDate(LocalDate time) {
        return fromDate.isEqual(time)
                || toDate.isEqual(time)
                || (fromDate.isBefore(time) && toDate.isAfter(time));
    }

    /**
     * Returns current data of event task.
     *
     * @return Current situation of event task.
     */
    @Override
    public String toString() {
        return "[" + TaskType.EVENT + "]" + super.toString() + " (from: "
                + fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy "))
                + (fromTime == null ? "" : fromTime.format(DateTimeFormatter.ofPattern("hh:mm a ")))
                + "to: " + toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy "))
                + (toTime == null ? "" : toTime.format(DateTimeFormatter.ofPattern("hh:mm a"))) + ")";
    }
}
