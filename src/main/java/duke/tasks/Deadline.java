package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private final LocalDate deadlineDue;
    private static final DateTimeFormatter DEADLINE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Deadline(String description, LocalDate deadlineDue) {
        super(description);
        this.deadlineDue = deadlineDue;
    }

    public static DateTimeFormatter getDeadlineFormatter() {
        return DEADLINE_FORMAT;
    }

    @Override
    public String getDataString() {
        return "D | " + super.getDataString() + " | " + this.deadlineDue.format(getDeadlineFormatter());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                deadlineDue.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
    }
}