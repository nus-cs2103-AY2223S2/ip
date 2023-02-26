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
     * @param description The Deadline task name.
     * @param bDate The Deadline date description of task.
     * @param bTime The Deadline time description of task.
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
     * Getter for byDate.
     *
     * @return The field byDate.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Getter for byTime.
     *
     * @return The field byTime.
     */
    public LocalTime getByTime() {
        return byTime;
    }

    /**
     * Checks whether the new task is already existed.
     *
     * @return The check result of whether duplicates exist.
     */
    @Override
    public boolean isDuplicate(Task task) {
        Deadline testTask = (Deadline) task;

        boolean isSameContent = testTask.getDescription().equals(getDescription());
        boolean isSameDate = testTask.getByDate().isEqual(byDate);
        boolean isSameTime;

        if (byTime != null && testTask.getByTime() != null) {
            isSameTime = testTask.getByTime().equals(byTime);
        } else {
            isSameTime = byTime == null && testTask.getByTime() == null;
        }

        return isSameContent && isSameDate && isSameTime;
    }

    /**
     * Returns comparison result of input time with task relevant time.
     *
     * @param time The user's input time.
     * @return The check result of whether the input time and task deadline are the same.
     */
    @Override
    public boolean isOnTime(LocalDate date, LocalTime time) {
        if (byTime == null) {
            return true;
        } else {
            LocalDateTime deadline = LocalDateTime.of(byDate, byTime);
            LocalDateTime testTime = LocalDateTime.of(date, time);

            return testTime.isBefore(deadline);
        }
    }

    /**
     * Generates a data string of deadline task to be stored in storage file.
     *
     * @return The data string of deadline task.
     */
    @Override
    public String toDataString() {
        return TaskType.DEADLINE + super.toDataString() + " @ " + byDate + " @ " + (byTime == null ? "0" : byTime);
    }

    /**
     * Returns comparison result of input date with task relevant date.
     *
     * @param date The user's input date.
     * @return The check result of whether the input date and task deadline are the same.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return byDate.isEqual(date);
    }

    /**
     * Returns code and task name in a string.
     *
     * @return The string of code and task name for schedule use.
     */
    @Override
    public String taskWithCode() {
        return "[" + TaskType.DEADLINE + "] " + getDescription();
    }

    /**
     * Returns current data of deadline task.
     *
     * @return The current situation of deadline task.
     */
    @Override
    public String toString() {
        String dateString = byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String timeString = (byTime == null ? "" : byTime.format(DateTimeFormatter.ofPattern(" hh:mm a")));

        return "[" + TaskType.DEADLINE + "]" + super.toString() + " (by: " + dateString + timeString + ")";
    }
}
