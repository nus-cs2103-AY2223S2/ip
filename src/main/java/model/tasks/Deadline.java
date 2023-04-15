package model.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import exceptions.DukeException;

public class Deadline extends Task {
    private final String icon = "[D]";
    protected LocalDateTime by;
    protected DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");

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
        return String.format("T|%s|%s|%s", getIsComplete(), formattedTime, getTaskDesc());
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
