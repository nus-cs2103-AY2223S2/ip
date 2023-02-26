package chattime.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
     * @param description The event task name.
     * @param frDate The start date description of task.
     * @param frTime The start time description of task.
     * @param tDate The deadline date description of task.
     * @param tTime The deadline time description of task.
     */
    public Event(String description, LocalDate frDate, LocalTime frTime, LocalDate tDate, LocalTime tTime) {
        super(description);

        fromDate = frDate;
        toDate = tDate;
        fromTime = frTime;
        toTime = tTime;
    }

    /**
     * Getter for fromDate.
     *
     * @return The field fromDate.
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Getter for fromTime.
     *
     * @return The field fromTime.
     */
    public LocalTime getFromTime() {
        return fromTime;
    }

    /**
     * Getter for toDate.
     *
     * @return The field toDate.
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Getter for toTime.
     *
     * @return The field toTime.
     */
    public LocalTime getToTime() {
        return toTime;
    }

    /**
     * Checks whether the new task is already existed.
     *
     * @return The check result of whether duplicates exist.
     */
    @Override
    public boolean isDuplicate(Task task) {
        Event testTask = (Event) task;

        boolean checkFrom = testTask.getFromDate().isEqual(fromDate) && testTask.getFromTime().equals(fromTime);
        boolean checkTo = testTask.getToDate().isEqual(toDate) && testTask.getToTime().equals(toTime);
        boolean isSameContent = task.getDescription().equals(getDescription());

        return isSameContent && checkFrom && checkTo;
    }

    /**
     * Returns comparison result of input time with task relevant time.
     *
     * @param time The user's input time.
     * @return The check result of whether the input time and task deadline are the same.
     */
    @Override
    public boolean isOnTime(LocalDate date, LocalTime time) {
        LocalDateTime start = LocalDateTime.of(fromDate, fromTime);
        LocalDateTime end = LocalDateTime.of(toDate, toTime);
        LocalDateTime test = LocalDateTime.of(date, time);

        boolean isInRange = test.isBefore(end) && test.isAfter(start);
        boolean isStart = test.isEqual(start);

        return isInRange || isStart;
    }

    /**
     * Generates a data string of event task to be stored in storage file.
     *
     * @return The data string of event task.
     */
    @Override
    public String toDataString() {
        return TaskType.EVENT + super.toDataString() + " @ " + fromDate + " @ " + fromTime
                + " @ " + toDate + " @ " + toTime;
    }

    /**
     * Returns comparison result of input date with task relevant date.
     *
     * @param date The user's input date.
     * @return The check result of whether the input date is in task time.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        Boolean isOnSameDate = fromDate.isEqual(date) || toDate.isEqual(date);
        Boolean isInDateRange = (fromDate.isBefore(date) && toDate.isAfter(date));

        return isOnSameDate || isInDateRange;
    }

    /**
     * Returns code and task name in a string.
     *
     * @return The string of code and task name for schedule use.
     */
    @Override
    public String taskWithCode() {
        return "[" + TaskType.EVENT + "] " + getDescription();
    }

    /**
     * Returns current data of event task.
     *
     * @return The current situation of event task.
     */
    @Override
    public String toString() {
        String fromDateString = fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String fromTimeString = (fromTime == null ? "" : fromTime.format(DateTimeFormatter.ofPattern(" hh:mma")));
        String toDateString = toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String toTimeString = (toTime == null ? "" : toTime.format(DateTimeFormatter.ofPattern(" hh:mma")));

        return "[" + TaskType.EVENT + "]" + super.toString()
                + " (from: " + fromDateString + fromTimeString + " to: " + toDateString + toTimeString + ")";
    }
}
