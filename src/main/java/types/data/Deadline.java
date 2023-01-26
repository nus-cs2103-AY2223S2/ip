package types.data;

import utilities.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime before;
    protected static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Deadline(String n, String d) {
        super(n, "D");
        before = DateTimeParser.parse(d);
    }

    public static Deadline create(String n, String d) {
        return new Deadline(n, d);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), before.format(format));
    }
}
