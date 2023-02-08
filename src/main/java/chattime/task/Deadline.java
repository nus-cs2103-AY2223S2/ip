package chattime.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents task object of 'deadline' type.
 */
public class Deadline extends Task {

    private LocalDate byDate;
    private LocalTime byTime;

    /**
     * Creates Deadline object with parent constructor and parsed description.
     *
     * @param description Deadline task name.
     * @param bDate Deadline date description of task.
     * @param bTime Deadline time description of task.
     */
    public Deadline(String description, LocalDate bDate, LocalTime... bTime) {
        super(description);

        byDate = bDate;
        try {
            byTime = bTime[0];
        } catch (IndexOutOfBoundsException e) {
            byTime = null;
        }
    }

    /**
     * Returns comparison result of input time with task relevant time.
     *
     * @param time User's input time.
     * @return true if the input time and task deadline are the same, otherwise false.
     */
    @Override
    public boolean isOnTime(LocalDate date, LocalTime time) {
        if (byTime == null) {
            return true;
        } else {
            LocalDateTime deadline = LocalDateTime.of(byDate, byTime);
            LocalDateTime test = LocalDateTime.of(date, time);
            return test.isBefore(deadline);
        }
    }

    /**
     * Generates a data string of deadline task to be stored in storage file.
     *
     * @return Data string of deadline task.
     */
    @Override
    public String toDataString() {
        return TaskType.DEADLINE + super.toDataString() + " @ " + byDate + " @ " + (byTime == null ? "0" : byTime);
    }

    /**
     * Returns comparison result of input date with task relevant date.
     *
     * @param date User's input date.
     * @return true if the input date and task deadline are the same, otherwise false.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return byDate.isEqual(date);
    }

    /**
     * Returns code and task name in a string.
     *
     * @return A string of code and task name for schedule use.
     */
    @Override
    public String taskWithCode() {
        return "[" + TaskType.DEADLINE + "] " + description;
    }

    /**
     * Returns current data of deadline task.
     *
     * @return Current situation of deadline task.
     */
    @Override
    public String toString() {
        String dateString = byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String timeString = (byTime == null ? "" : byTime.format(DateTimeFormatter.ofPattern(" hh:mm a")));

        return "[" + TaskType.DEADLINE + "]" + super.toString() + " (by: " + dateString + timeString + ")";
    }
}
