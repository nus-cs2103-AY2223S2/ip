package task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import util.DukeException;

/**
 * Represents a task that the user wishes to add to
 * the list. A task object has a completion status and
 * a description.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Initialises a task.
     * <p>
     * @param description
     * @param status
     */
    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    public String getStatusIcon() {
        //mark done task with X
        return (isDone ? "X" : " ");
    }

    public Boolean getStatus() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Formats the date and time input by the user while creating the
     * task into a more readable format i.e. 1 Jan 2021 11:59 PM.
     *
     * @param str
     * @return Formatted date and time
     * @throws DukeException
     */
    public String dateFormatter(String str) throws DukeException {
        //"d/M/y H:mm" for auto detection of AM/PM d/M/yy h:mma for manual but in 12hr time
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("d/M/yy Hmm"));
            assert localDateTime != null;
            String dt = localDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a"));
            assert dt != null;
            return dt;
        } catch (DateTimeException e) {
            throw new DukeException("Please enter date in dd/mm/yy and time in hhmm 24hr format!");
        }
    }

    /**
     * Encodes task data for storage in a file.
     *
     * @return Encoded String representation
     */
    public String serialise() {
        return String.format("Task,%s,%s", this.getStatus(), this.description);
    }

    /**
     * Decodes a String representation of a task in the file
     * back into the Task object.
     *
     * @param data
     * @return Decoded Task object
     * @throws DukeException
     */
    public static Task deserialise(String data) throws DukeException {
        String[] arr = data.split(",");
        assert arr.length != 0;
        boolean isDone = Boolean.parseBoolean(arr[1]);
        String desc = arr[2];

        return new Task(desc, isDone);
    }

    /**
     * String representation of a Task object.
     *
     * @return String representation of a Task object.
     */
    @Override
    public String toString() {
        String str = "[" + this.getStatusIcon() + "] " + this.description;
        return str;
    }
}
