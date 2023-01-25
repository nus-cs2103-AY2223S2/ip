package chattime.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents task object of 'deadline' type.
 */
public class Deadline extends Task {

    private final LocalDate byDate;
    private final LocalTime byTime;

    /**
     * Creates Deadline object with parent constructor and parsed description.
     *
     * @param description Deadline task name.
     * @param bDate Deadline date description of task.
     * @param bTime Deadline time description of task.
     */
    public Deadline(String description, LocalDate bDate, LocalTime bTime) {
        super(description);
        byDate = bDate;
        byTime = bTime;
    }

    /**
     * Generates a data string of deadline task to be stored in storage file.
     *
     * @return Data string of deadline task.
     */
    @Override
    public String toDataString() {
        return "D" + super.toDataString() + " @ " + byDate + " @ " + (byTime == null ? "0" : byTime);
    }

    /**
     * Returns comparison result of input time with task relevant time.
     *
     * @param time User's input time.
     * @return true if the input time and task deadline are the same, otherwise false.
     */
    @Override
    public boolean isOnDate(LocalDate time) {
        return byDate.isEqual(time);
    }

    /**
     * Returns current data of deadline task.
     *
     * @return Current situation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy "))
                + (byTime == null ? "" : byTime.format(DateTimeFormatter.ofPattern("hh:mm a"))) + ")";
    }
}
