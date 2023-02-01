package classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a task that is of type "event" and extends from Task class.
 *
 * @author MrTwit99
 * @since 2023-02-01
 */
public class Event extends Task {
    protected String newStartDate, newEndDate, newStartTime, newEndTime,
            oldStartDate, oldEndDate, oldStartTime, oldEndTime;

    protected LocalTime startTime, endTime;

    protected LocalDate startDate, endDate;

    /**
     * Returns an Event task object that stores information about the task: task description, start date,
     * start time, end date and endTime.
     * <p></p>
     * Whenever start time and end time not given, set them to be empty.
     *
     * @param taskInfo Task description.
     * @param stringStartDate Date that the Event begins.
     * @param stringEndDate Date that the Event ends.
     * @param stringStartTime Start time of the Event.
     * @param stringEndTime End time of the Event.
     */
    public Event(String taskInfo, String stringStartDate, String stringEndDate, String stringStartTime
            , String stringEndTime) {
        super(taskInfo);
        this.oldStartDate = stringStartDate;
        this.oldEndDate = stringEndDate;
        this.oldStartTime = " " + stringStartTime;
        this.oldEndTime = " " + stringEndTime;
        this.startDate = LocalDate.parse(stringStartDate);
        this.newStartDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(startDate);
        this.endDate = LocalDate.parse(stringEndDate);
        this.newEndDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(endDate);

        if (stringStartTime.equals("")) {
            this.startTime = null;
            this.newStartTime = "";
            this.oldStartTime = "";
        } else {
            this.startTime = LocalTime.parse(stringStartTime);
            this.newStartTime = " | " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                    .format(startTime);
        }

        if (stringEndTime.equals("")) {
            this.endTime = null;
            this.newEndTime = "";
            this.oldEndTime = "";
        } else {
            this.endTime = LocalTime.parse(stringEndTime);
            this.newEndTime = " | " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                    .format(endTime);
        }
    }

    /**
     * Returns a string on the information about the Event task that is to be added to the ongoing taskList.
     *
     * @return String message of the Event task description and status.
     */
    @Override
    public String getTaskInfoStatus() {
        return "[E]" + super.getTaskInfoStatus() + "(from: " + this.newStartDate + this.newStartTime + " to: "
                + this.newEndDate + this.newEndTime + ")";
    }

    /**
     * Returns a string on the information about the Event task that is to be saved to the file allocated by Storage.
     *
     * @return String message of the Event task description and status.
     */
    @Override
    public String getTaskInfo() {
        return "[E]" + super.getTaskInfoStatus() + "/from " + this.oldStartDate + this.oldStartTime + " /to "
                + this.oldEndDate + this.oldEndTime;
    }
}
