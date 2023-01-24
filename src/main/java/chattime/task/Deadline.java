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
     * @param byDate Deadline date description of task.
     * @param byTime Deadline time description of task.
     */
    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Generates a data string of deadline task to be stored in storage file.
     *
     * @return Data string of deadline task.
     */
    @Override
    public String toDataString() {
        return "D" + super.toDataString() + " @ " + this.byDate + " @ " + (this.byTime == null ? "0" : this.byTime);
    }

    /**
     * Returns comparison result of input time with task relevant time.
     *
     * @param time User's input time.
     * @return true if the input time and task deadline are the same, otherwise false.
     */
    @Override
    public boolean onDate(LocalDate time) {
        return this.byDate.isEqual(time);
    }

    /**
     * Returns current data of deadline task.
     *
     * @return Current situation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy ")) +
                (this.byTime == null ? "" : this.byTime.format(DateTimeFormatter.ofPattern("hh:mm a"))) + ")";
    }
}
