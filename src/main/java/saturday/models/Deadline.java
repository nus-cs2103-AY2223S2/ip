package saturday.models;

import java.time.temporal.TemporalAccessor;

import saturday.utilities.DateTimeParser;
public class Deadline extends Task {
    private TemporalAccessor deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = DateTimeParser.parseDate(deadline);
    }

    public TemporalAccessor getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.printDateTime(deadline) + ")";
    }
}
