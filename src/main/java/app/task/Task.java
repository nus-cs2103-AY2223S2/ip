package app.task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import app.chatbot.Storage;

/**
 * Represents a Task, which can contain additional attributes, such as the
 * starting and ending datetime.
 * A Task is not done by default.
 * A description for the Task is compulsory.
 * <br>
 * Supported DateTime formats are included in this class for Tasks that
 * have DateTime attributes.
 * <br>
 * Attributes of a Task cannot be accessed and modified directly from other packages
 * (use the TaskList to modify a Task).
 */
public abstract class Task {
    protected static final List<DateTimeFormatter> SUPPORTED_DATE_TIME_INPUT = new ArrayList<>();
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("EEE, dd MMM yy hh:mma");

    protected String description;
    protected boolean isDone;
    protected String symbol;
    static {
        SUPPORTED_DATE_TIME_INPUT.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        SUPPORTED_DATE_TIME_INPUT.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        SUPPORTED_DATE_TIME_INPUT.add(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Returns the status icon for display. Marks a task as done with "X", blank otherwise.
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDesc() {
        return this.description;
    }

    /**
     * Marks task as done
     */
    protected void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as undone
     */
    protected void unmarkDone() {
        isDone = false;
    }

    /**
     * Formats the task as data to be stored in text file.
     * @return data format recognisable by the app .
     */
    public abstract String asDataFormat();


    /**
     * Formats the task as data to be stored in text file.
     * @param fields variable number of fields (attributes) of the task.
     * @return data format recognisable by the app.
     */
    protected String asDataFormat(String... fields) {
        String base = String.join(Storage.SEPARATOR, this.symbol, this.isDone() ? "1" : "0", this.description);
        for (String s : fields) {
            if (!s.isBlank()) {
                base = String.join(Storage.SEPARATOR, base, s);
            }
        }
        return base;
    }

    /**
     * @return user-readable format with information regarding the task.
     */
    @Override
    public String toString() {
        return "[" + this.symbol + "][" + this.getStatusIcon() + "] " + this.description;
    }
}
