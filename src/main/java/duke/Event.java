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
        this.startDate = LocalDate.parse(stringStartDate);
        this.formatStartDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(startDate);
        this.endDate = LocalDate.parse(stringEndDate);
        this.formatEndDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(endDate);
        this.startTime = this.localTimeParser(stringStartTime);
        this.formatStartTime = this.formatTime(startTime);
        this.endTime = this.localTimeParser(stringEndTime);
        this.formatEndTime = this.formatTime(endTime);
        this.originalStartTime = this.formatOriginalTime(stringStartTime, formatStartTime);
        this.originalEndTime = this.formatOriginalTime(stringEndTime, formatEndTime);
    }

    /**
     * Return boolean value indicating whether the current Task is ongoing.
     *
     * @return Boolean value indicating whether the current Task is ongoing.
     */
    @Override
    public boolean isOngoing() {
        LocalDate currDate = LocalDate.now();
        LocalTime currTime = LocalTime.now();
        if (endDate.compareTo(currDate) >= 0) {
            if (endTime == null) {
                return true;
            } else if (endTime.compareTo(currTime) >= 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Returns a LocalTime object that corresponds to the parsed String time given.
     *
     * @param time String which represents the time that needs to be parsed into a LocalTime object.
     * @return LocalTime object storing the parsed time.
     */
    private LocalTime localTimeParser(String time) {
        if (time.equals("")) {
            return null;
        }
        return LocalTime.parse(time);
    }

    /**
     * Returns a String that corresponds to the time given in the LocalTime object after formatting.
     *
     * @param time LocalTime object storing the time.
     * @return String which represents the time.
     */
    private String formatTime(LocalTime time) {
        if (time == null) {
            return "";
        }
        return " | " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                .format(time);
    }

    /**
     * Returns a String representing the formatted originalTime by checking it with the given String formatTime.
     *
     * @param originalTime String representing the original time.
     * @param formatTime String representing the formatTime that needs to be checked to determine the format of output
     *                   time.
     * @return String which represents the original time with format.
     */
    private String formatOriginalTime(String originalTime, String formatTime) {
        if (formatTime.equals("")) {
            return formatTime;
        }
        return " " + originalTime;
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

    /**
     * Returns a boolean value indicating the completion status of the Task.
     *
     * @return Boolean value indicating the completion status of the Task.
     */
    public boolean getStatus() {
        return super.getStatus();
    }
}
