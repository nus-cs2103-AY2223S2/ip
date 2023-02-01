package classes;

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
    protected String formatDate, formatTime, originalDate, originalTime;
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

        if (dueTime.equals("")) {
            this.time = null;
            this.formatTime = "";
        } else {
            this.time = LocalTime.parse(dueTime);
            this.formatTime = " | " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                    .format(time);
        }
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
}
