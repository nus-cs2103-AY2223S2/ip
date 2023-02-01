package types.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import utilities.DateTimeParser;

/**
 * Deadline data type.
 */
public class Deadline extends Task {
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final LocalDateTime before;

    private Deadline(String n, String d) {
        super(n, "D");
        before = DateTimeParser.parse(d);
    }

    /**
     * Factory method to get a new Deadline object.
     * @param n Description.
     * @param d Deadline date or time.
     * @return Newly created Deadline object.
     */
    public static Deadline create(String n, String d) {
        return new Deadline(n, d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), before.format(DATE_TIME_FORMATTER));
    }
}
