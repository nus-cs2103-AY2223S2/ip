package duke.task;

import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An abstract class Task
 */
public abstract class Task {
    private static final String DATE_TIME_DISPLAY_FORMAT =  "MMM dd yyyy HH:mm";

    protected String description;
    protected boolean isDone;

    Task(String description) {
        this(description, false);
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks task as completed.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void undoTask() {
        this.isDone = false;
    }
    String displayDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_DISPLAY_FORMAT);
        return dateTime.format(formatter);
    }

    String formatSavedDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Parser.DATE_TIME_READ_FORMAT);
        return dateTime.format(formatter);
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns String representation of task to be saved in file.
     *
     * @return String representation of task to be saved in file.
     */

    public abstract String getSaveTaskString();

    /**
     * Returns String representation of task to be printed.
     *
     * @return String representation of task to be printed.
     */
    @Override
    public String toString() {
        return String.format("%s | %s", isDone ? "X" : " ", description);
    }
}
