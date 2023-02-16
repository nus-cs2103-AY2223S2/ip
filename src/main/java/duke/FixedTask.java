package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FixedTask is a type of task that has a fixed duration time.
 */
public class FixedTask extends Task {

    private String hours;

    public FixedTask(String description, String hours) {
        super(description);
        this.hours = hours;
    }

    @Override
    public String getIcon() {
        return "F";
    }

    @Override
    public String toString() {
        return description + " (needs " + hours + " hours)";
    }

    public String getHours() {
        return hours;
    }

}
