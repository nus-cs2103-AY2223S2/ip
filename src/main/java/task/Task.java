package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    public static final String DATE_IN_FMT_STR = "yyyy-MM-dd HH:mm";
    public static final String DATE_OUT_FMT_STR = "dd LLL yyyy hh:mm a";
    protected static final DateTimeFormatter DATE_IN_FMT = DateTimeFormatter.ofPattern(DATE_IN_FMT_STR);
    protected static final DateTimeFormatter DATE_OUT_FMT = DateTimeFormatter.ofPattern(DATE_OUT_FMT_STR);

    protected String objective;
    protected boolean isDone;

    public Task(String objective) {
        this.objective = objective;
        this.isDone = false;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public abstract String[] save();

    public abstract boolean isBeforeDate(LocalDateTime date);

    public abstract boolean isAfterDate(LocalDateTime date);

    @Override
    public String toString() {
        return "[" + (isDone ? 'X' : ' ') + "] " + objective;
    }
}
