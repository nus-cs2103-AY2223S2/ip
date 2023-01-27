package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDateTime end;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter FORMATTER_FOR_STRING_ONLY = DateTimeFormatter.ofPattern("d MMM uuuu h.mma");


    /**
     * duke.Deadline constructor.
     *
     * @param desc Description of what the deadline is.
     * @param isDone True if the deadline is done. false if the deadline is not done.
     * @param end Date the deadline is due.
     */
    public Deadline(String desc, boolean isDone, LocalDateTime end) {
        super(desc, isDone);
        this.end = end;
    }

    /**
     * Returns the string to be inserted into the duke file for storage.
     *
     * @return The deadline string.
     */
    public String formatStringForFile() {
        return String.format("DEADLINE / %s / %s", super.helpFormatString(),
                this.end.format(FORMATTER));
    }

    /**
     * Overriding the toString function to contain the type of task being created.
     *
     * @return String of the deadline being created.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.end.format(FORMATTER_FOR_STRING_ONLY) + ")";
    }
}
