package donkeychat;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Task {

    protected String description;
    protected boolean isDone;
    protected final DateTimeFormatter FORMAT_PRINT = DateTimeFormatter.ofPattern("d-MMM-yyyy[ HH:mm]");
    protected final DateTimeFormatter FORMAT_PARSE = new DateTimeFormatterBuilder()
        .appendPattern("dd-MM-yyyy[ HH:mm]")
        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
        .toFormatter();
    protected final DateTimeFormatter FORMAT_DATE_TIME = new DateTimeFormatterBuilder()
        .appendOptional(FORMAT_PRINT)
        .appendOptional(FORMAT_PARSE)
        .toFormatter();


    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Generates a serialized form of the task.
     */
    public String serialize() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}


