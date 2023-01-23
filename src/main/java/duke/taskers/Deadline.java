package duke.taskers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDateTime end;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM uuuu h.mm a");

    public Deadline(String desc, boolean isDone, LocalDateTime end) {
        super(desc, isDone);
        this.end = end;
    }

    public String statusStringForFile() {
        return String.format("DEADLINE / %s / %s", super.stringFormatForFile(), this.end.format(FORMATTER));
    }

    /**
     * overriding the toString function to contain the type of task being created
     * @return string of the deadline being created
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.end.format(FORMATTER) + ")";
    }
}
