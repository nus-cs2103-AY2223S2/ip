package tasks;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a task to be completed.
 */
public abstract class Task {
    /**
     * Formatters to parse date time strings.
     */
    private static final DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    /**
     * Description of the task.
     */
    protected String description;
    /**
     * Whether the task is done or not.
     */
    protected boolean isDone;

    /**
     * Constructs a new task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the date time formatter for input.
     * @return Date time formatter for input.
     */
    public static DateTimeFormatter getInputDateTimeFormatter() {
        return inputDateTimeFormatter;
    }

    /**
     * Gets the date time formatter for output.
     * @return Date time formatter for output.
     */
    public static DateTimeFormatter getOutputDateTimeFormatter() {
        return outputDateTimeFormatter;
    }

    /**
     * Sets a task as complete or uncomplete.
     *
     * @param done Whether to set the task as complete or uncomplete.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Get a human readable representation of whether the task is complete or not.
     *
     * @return A human readable representation of whether the task is complete or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Check if a task contains a given string.
     *
     * @param toMatch String to check for.
     * @return Whether the task contains the given string or not.
     */
    public boolean match(String toMatch) {
        Pattern pattern = Pattern.compile(toMatch);
        Matcher matcher = pattern.matcher(this.description);
        return matcher.find();
    }

    /**
     * Represents the task in a string suitable for storing in memory.
     *
     * @return A string representing the task suitable for storing in memory.
     */
    public abstract String toEncodedString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
