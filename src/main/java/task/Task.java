package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String objective;
    protected boolean done;

    public static final String DATE_IN_FMT_STR = "yyyy-MM-dd HH:mm";
    public static final String DATE_OUT_FMT_STR = "dd LLL yyyy hh:mm a";
    public static final DateTimeFormatter DATE_IN_FMT = DateTimeFormatter.ofPattern(DATE_IN_FMT_STR);
    protected static final DateTimeFormatter DATE_OUT_FMT = DateTimeFormatter.ofPattern(DATE_OUT_FMT_STR);

    public Task(String objective) {
        this.objective = objective;
        this.done = false;
    }

    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public abstract String[] save();

    public abstract boolean beforeDate(LocalDateTime date);
    public abstract boolean afterDate(LocalDateTime date);
    public boolean hasMatchingObjective(String regex) {
        return objective.matches(regex);
    }

    @Override
    public String toString() {
        return "[" + (done ? 'X' : ' ') + "] " + objective;
    }
}
