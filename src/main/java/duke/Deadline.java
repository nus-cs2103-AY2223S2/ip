package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a task that is of type "deadline" and extends from Task class.
 *
 * @author MrTwit99
 * @since 2023-02-01
 */
public class Deadline extends Task {
    protected String formatDate;
    protected String formatTime;
    protected String originalDate;
    protected String originalTime;
    protected LocalDate date;

    protected LocalTime time;

    /**
     * Returns a Deadline task object that stores information about the task: task description,
     * its due date and time.
     * <p></p>
     * Whenever due time not given, set the time to be empty
     *
     * @param taskInfo Task Description.
     * @param dueDate Due date for the task.
     * @param dueTime Due time for the task.
     */
    public Deadline(String taskInfo, String dueDate, String dueTime) {
        super(taskInfo);
        this.originalDate = dueDate;
        this.originalTime = dueTime;
        this.date = LocalDate.parse(dueDate);
        this.formatDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(date);

        this.time = this.localTimeParser(dueTime);
        this.formatTime = this.formatTime(this.time);
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
        if (date.compareTo(currDate) >= 0) {
            if (time == null) {
                return true;
            } else if (time.compareTo(currTime) >= 0) {
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
     * Returns a string on the information about the Deadline task that is to be added to the ongoing taskList.
     *
     * @return String message of the Deadline task description and status.
     */
    @Override
    public String getTaskInfoStatus() {
        return "[D]" + super.getTaskInfoStatus() + "(by: " + this.formatDate + this.formatTime + ")";
    }

    /**
     * Returns a string on the information about the Deadline task that is to be saved to the file allocated by Storage.
     *
     * @return String message of the Deadline task description and status.
     */
    @Override
    public String getTaskInfo() {
        return "[D]" + super.getTaskInfoStatus() + "/by " + this.originalDate + " " + this.originalTime;
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
