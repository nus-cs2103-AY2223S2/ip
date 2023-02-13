package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM YY");

    private final LocalDate end;

    /**
     * @param desc Task description
     * @param done Task completed info
     * @param end  Task due date
     * @see LocalDate
     */
    private Deadline(String desc, boolean done, LocalDate end) {
        super(desc, done);
        this.end = end;
    }

    /**
     * @param desc Description of task
     * @param end  Due date of task
     * @see LocalDate
     */
    public Deadline(String desc, LocalDate end) {
        this(desc, false, end);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.end.format(DATE_FORMAT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDate(LocalDate date) {
        return this.end.isEqual(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deadline markDone() {
        return new Deadline(this.desc, true, this.end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deadline markNotDone() {
        return new Deadline(this.desc, false, this.end);
    }
}
