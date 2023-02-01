package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task object.
 */
public abstract class Task {
    private boolean isDone;
    private String title;

    /**
     * Constructs an unchecked Task object with a title.
     * @param title Title of the Task object.
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    };

    /**
     * Constructs a Task object with a title, and it is checked or unchecked depending on the value of isDone.
     * @param title Title of Task object.
     * @param isDone Boolean to indicate if the Deadline should be checked or not.
     */
    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return this.title;
    }
    public boolean getIsDone() {
        return this.isDone;
    }
    public void setIsDone(boolean status) {
        this.isDone = status;
    }

    public abstract String toDiskFormat();
    protected LocalDateTime parseDateString(String dateString) throws DateTimeParseException {
        return LocalDateTime.parse(dateString);
    }
    protected String dateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu, HH.mm a"));
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "x" : " ", this.title);
    }

}
