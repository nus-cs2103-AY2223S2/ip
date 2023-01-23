package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** A specification for a Task in Duke. */
public abstract class Task {

    // date formats and formatters
    public static final String DT_INPUT_FORMAT = "dd-MM-yyyy";
    public static final String DT_PRINT_FORMAT = "d MMM yyyy";
    public static final DateTimeFormatter DT_INPUT_FORMATTER = DateTimeFormatter.ofPattern(DT_INPUT_FORMAT);
    public static final DateTimeFormatter DT_PRINT_FORMATTER = DateTimeFormatter.ofPattern(DT_PRINT_FORMAT);

    protected boolean isDone;
    protected String name;

    /** Creates a task with a given name and completion status. */
    public Task(String name, boolean isDone) {
        this.isDone = isDone;
        this.name = name;
    }

    /**
     * Parses a date from a string using the date formatter.
     * 
     * @param dateString The string to be parsed
     * @return The date represented by the given string
     */
    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, Task.DT_INPUT_FORMATTER);
    }

    /**
     * Get a string representation of a date using the date formatter.
     * 
     * @param date The date to be formatted
     * @return The formatted string representation of that date
     */
    public static String formatDate(LocalDate date) {
        return date.format(DT_PRINT_FORMATTER);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    /**
     * Gets a string representation of the task type.
     * 
     * @return A string representation of the task type.
     */
    protected abstract String getTaskType();

    /**
     * Serializes the task object into a string.
     * 
     * @return A string representation of the task object.
     */
    public String serialize() {
        String serialized = String.format("%s|%s|%s", this.getTaskType(), this.isDone ? 1 : 0, this.name);
        return serialized;
    }

    @Override
    public String toString() {
        String s = String.format("[%s][%s] %s", this.getTaskType(), this.getStatusIcon(), this.name);
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return this.name.equals(task.name) && (this.isDone == task.isDone);
    }
}
