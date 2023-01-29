package donkeychat;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Task {

    protected String description;
    protected boolean isDone;
    protected final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("d-MMM-yyyy[ HH:mm]");
    protected final DateTimeFormatter PARSE_FORMAT = new DateTimeFormatterBuilder()
        .appendPattern("dd-MM-yyyy[ HH:mm]")
        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
        .toFormatter();
    protected final DateTimeFormatter DATE_TIME_FORMAT = new DateTimeFormatterBuilder()
        .appendOptional(PRINT_FORMAT)
        .appendOptional(PARSE_FORMAT)
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

    public String serialize() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}


