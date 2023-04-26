package model.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Class representing a DoAfter task
 */
public class DoAfter extends Task {
    protected DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");
    protected LocalDateTime by;
    private final String icon = "[A]";

    /**
     * Constructor for DoAfter
     * @param description The description of the DoAfter
     * @param by The deadline of the DoAfter
     */
    public DoAfter(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for DoAfter
     * @param description The description of the DoAfter
     * @param by The deadline of the DoAfter
     * @param status The status of the DoAfter
     */
    public DoAfter(String description, LocalDateTime by, Boolean status) {
        super(description);
        setIsComplete(status);
        this.by = by;
    }

    /**
     * Returns the string representation of the DoAfter to be saved
     * @return String
     */
    @Override
    public String savedAs() {
        String formattedTime = this.by.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        return String.format("A|%s|%s|%s", getIsComplete(), getTaskDesc(), formattedTime);
    }

    /**
     * Returns a string representation of this DoAfter task
     * @return String
     */
    @Override
    public String toString() {
        String formattedTime = this.by.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        return String.format("%s%s (after: %s)", this.icon, super.toString(), formattedTime);
    }
}
