package duke.taskers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDateTime end;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("d MMM uuuu h.mma");

    /**
     * deadline constructor
     * @param desc description of what the deadline is
     * @param isDone true if the deadline is done. false if the deadline is not done
     * @param end date the deadline is due
     */
    public Deadline(String desc, boolean isDone, LocalDateTime end) {
        super(desc, isDone);
        this.end = end;
    }

    /**
     * returns the string to be inserted into the duke file for storage
     * @return the deadline string
     */
    public String formatStringForFile() {
        return String.format("DEADLINE / %s / %s", super.helpFormatString(),
                this.end.format(FORMATTER));
    }

    /**
     * overriding the toString function to contain the type of task being created
     * @return string of the deadline being created
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.end.format(FORMATTER) + ")";
    }
}
