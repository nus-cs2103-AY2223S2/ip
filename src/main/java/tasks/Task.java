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
     * A positive integer representing how important the task is, where lower values are more important.
     */
    protected int priority;

    /**
     * Constructs a new task.
     *
     * @param description Description of the task.
     * @param priority Priority of the task.
     */
    public Task(String description, int priority) {
        this.description = description;
        this.isDone = false;
        assert priority >= 0 : "Attempted to create task with negative priority";
        this.priority = priority;
    }

    /**
     * Gets the date time formatter for input.
     *
     * @return Date time formatter for input.
     */
    public static DateTimeFormatter getInputDateTimeFormatter() {
        return inputDateTimeFormatter;
    }

    /**
     * Gets the date time formatter for output.
     *
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
     * Sets the priority of a task.
     *
     * @param priority The priority level to set the task to.
     */
    public void setPriority(int priority) {
        // Checking for negative priority is done in Parser
        assert priority >= 0 : "Attempted to set negative priority";
        this.priority = priority;
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
        return "[" + this.getStatusIcon() + "]" + "[" + this.priority + "]" + " " + this.description;
    }
}
