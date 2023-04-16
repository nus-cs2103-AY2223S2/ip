package model.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Class representing a Deadline task
 */
public class Deadline extends Task {
    protected DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");
    protected LocalDateTime by;
    private final String icon = "[D]";

    /**
     * Constructor for Deadline
     * @param description The description of the Deadline
     * @param by The deadline of the Deadline
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline
     * @param description The description of the Deadline
     * @param by The deadline of the Deadline
     * @param status The status of the Deadline
     */
    public Deadline(String description, LocalDateTime by, Boolean status) {
        super(description);
        setIsComplete(status);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline to be saved
     * @return String
     */
    @Override
    public String savedAs() {
        String formattedTime = this.by.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        return String.format("D|%s|%s|%s", getIsComplete(), getTaskDesc(), formattedTime);
    }

    /**
     * Returns a string representation of this Deadline task
     * @return String
     */
    @Override
    public String toString() {
        String formattedTime = this.by.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        return String.format("%s%s (by: %s)", this.icon, super.toString(), formattedTime);
    }
}
