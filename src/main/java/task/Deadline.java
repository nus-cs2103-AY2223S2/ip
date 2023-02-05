package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM YY");

    private final LocalDate end;

    private Deadline(String desc, boolean done, LocalDate end) {
        super(desc, done);
        this.end = end;
    }

    public Deadline(String desc, LocalDate end) {
        this(desc, false, end);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.end.format(DATE_FORMAT));
    }

    @Override
    public boolean hasDate(LocalDate date) {
        return this.end.isEqual(date);
    }

    @Override
    public Deadline markDone() {
        return new Deadline(this.desc, true, this.end);
    }

    @Override
    public Deadline markNotDone() {
        return new Deadline(this.desc, false, this.end);
    }
}
