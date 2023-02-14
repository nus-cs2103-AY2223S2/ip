package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.exceptions.DukeSaveLoadException;

/**
 * A task.
 */
public abstract class Task {
    /** The description of the task. */
    public final String description;
    /** Whether the task is done. */
    protected boolean isDone;

    /**
     * Creates a new task.
     * 
     * @param description Task's description.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Encodes an array of arbitrary values into a single-line string for saving
     * into a file. Since the delimiter " | " is used in the encoding, any vertical
     * bar "|" in the values is escaped.
     * 
     * @param values The array of values to encode.
     * @return The encoded values.
     */
    protected static String encodeValues(String[] values) {
        UnaryOperator<String> escapeVerticalBar = str -> str.replace("|", "\\|");
        return Stream.of(values)
                .map(escapeVerticalBar)
                .collect(Collectors.joining(" | "));
    }

    /**
     * Decodes an encoded string of values (encoded by 'encodeValues') into an
     * array. Since the delimiter " | " is used in the encoding, any escaped
     * vertical bar "|" in encoded string is unescaped before returning.
     * 
     * @param encodedValues The encoded values.
     * @return The deencoded array of values.
     */
    protected static String[] decodeValues(String encodedValues) {
        UnaryOperator<String> unescapeVerticalBar = str -> str.replace("\\|", "|");
        return Stream.of(encodedValues.split(" \\| "))
                .map(unescapeVerticalBar)
                .toArray(String[]::new);
    }

    /**
     * Parses a task that has been encoded into a string, into a 'Task' instance.
     * 
     * @param input The encoded task.
     * @return The task that was encoded.
     * @throws DukeSaveLoadException If there's a problem in parsing the encoded
     *         task.
     */
    public static Task loadFromString(String input) throws DukeSaveLoadException {
        String[] values = Task.decodeValues(input);
        String taskType = values[0];
        switch (taskType) {
        case "T":
            return TaskTodo.loadFromString(input);
        case "D":
            return TaskDeadline.loadFromString(input);
        case "E":
            return TaskEvent.loadFromString(input);
        default:
            String errorMessage = String.format("Unknown task in save-file: \"%s\"", input);
            throw new DukeSaveLoadException(errorMessage);
        }
    }

    /**
     * Encodes this task into a string.
     * 
     * @return The encoded task.
     */
    public abstract String encodeAsString();

    @Override
    public String toString() {
        String statusIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", statusIcon, description);
    }

    /**
     * Formats a date for displaying in Duke.
     * 
     * @param date The date to format.
     * @return The formatted date string.
     */
    protected static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        if (other.isDone != isDone) {
            return false;
        }
        if (!other.description.equals(description)) {
            return false;
        }
        return true;
    }
}
