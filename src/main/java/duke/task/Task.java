package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task added by the user.
 * It has a description attached to it and a isDone status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns X if task is marked done.
     * If task is unmarked, a space is returned.
     *
     * @return Character to indicate the status of the task.
     */
    public char getStatusIcon() {
        return (isDone) ? 'X' : ' ';
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of a Task.
     *
     * @return String representation of a Task in this format: [{status}] {description}.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns the raw String representation of a Task to be stored in the local file for storage.
     *
     * @return Raw String representation of a Task in this format: T ~ {status} ~ {description}.
     */
    public String getRawTask() {
        return String.format("T ~ %d ~ %s\n", isDone ? 1 : 0, this.description);
    }

    /**
     * Returns the valid format of the datetime of the Task.
     * Valid format: {yyyy-MM-dd HH:mm}
     *
     * @param input Datetime input provided by user.
     * @return Datetime of Task in valid format.
     */
    public LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        return dateTime;
    }

    /**
     * Returns datetime in String for printing.
     *
     * @param dateTime Datetime of Task.
     * @return String representation of datetime.
     */
    public String getStringDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM-yyyy HH:mma");
        return dateTime.format(formatter);
    }
}
