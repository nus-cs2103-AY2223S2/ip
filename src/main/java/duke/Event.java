package duke;

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
    protected String formatStartDate;
    protected String formatEndDate;
    protected String formatStartTime;
    protected String formatEndTime;
    protected String originalStartDate;
    protected String originalEndDate;
    protected String originalStartTime;
    protected String originalEndTime;

    protected LocalTime startTime;
    protected LocalTime endTime;

    protected LocalDate startDate;
    protected LocalDate endDate;

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
    public Event(String taskInfo, String stringStartDate, String stringEndDate,
                 String stringStartTime, String stringEndTime) {
        super(taskInfo);
        this.originalStartDate = stringStartDate;
        this.originalEndDate = stringEndDate;
        this.originalStartTime = " " + stringStartTime;
        this.originalEndTime = " " + stringEndTime;
        this.startDate = LocalDate.parse(stringStartDate);
        this.formatStartDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(startDate);
        this.endDate = LocalDate.parse(stringEndDate);
        this.formatEndDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(endDate);

        if (stringStartTime.equals("")) { // no startTime provided
            this.startTime = null;
            this.formatStartTime = "";
            this.originalStartTime = "";
        } else {
            this.startTime = LocalTime.parse(stringStartTime);
            this.formatStartTime = " | " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                    .format(startTime);
        }

        if (stringEndTime.equals("")) { // no endTime provided
            this.endTime = null;
            this.formatEndTime = "";
            this.originalEndTime = "";
        } else {
            this.endTime = LocalTime.parse(stringEndTime);
            this.formatEndTime = " | " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
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
        return "[E]" + super.getTaskInfoStatus() + "(from: " + this.formatStartDate + this.formatStartTime + " to: "
                + this.formatEndDate + this.formatEndTime + ")";
    }

    /**
     * Returns a string on the information about the Event task that is to be saved to the file allocated by Storage.
     *
     * @return String message of the Event task description and status.
     */
    @Override
    public String getTaskInfo() {
        return "[E]" + super.getTaskInfoStatus() + "/from " + this.originalStartDate + this.originalStartTime + " /to "
                + this.originalEndDate + this.originalEndTime;
    }
}
