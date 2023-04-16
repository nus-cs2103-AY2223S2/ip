package model.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Deadline extends Task {
    private final String icon = "[D]";
    protected LocalDateTime by;
    protected DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, Boolean status) {
        super(description);
        setIsComplete(status);
        this.by = by;
    }

    @Override
    public String savedAs() {
        String formattedTime = this.by.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        return String.format("D|%s|%s|%s", getIsComplete(), getTaskDesc(), formattedTime);
    }

    /**
     * Returns a string representation of this To-Do task
     * @return String
     */
    @Override
    public String toString() {
        String formattedTime = this.by.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        return String.format("%s%s (by: %s)", this.icon, super.toString(), formattedTime);
    }
}
