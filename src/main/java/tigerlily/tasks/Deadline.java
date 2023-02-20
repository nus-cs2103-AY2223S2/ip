package tigerlily.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private final LocalDate deadlineDue;
    private static final DateTimeFormatter DEADLINE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructor for Deadline
     * @param description the description of the Deadline
     * @param deadlineDue the date when the Deadline is due
     */
    public Deadline(String description, LocalDate deadlineDue) {
        super(description);
        this.deadlineDue = deadlineDue;
    }

    /**
     * Returns the DateTimeFormatter used to format Deadlines.
     *
     * @return the DateTimeFormatter used to format Deadlines
     */
    public static DateTimeFormatter getDeadlineFormatter() {
        return DEADLINE_FORMAT;
    }

    /**
     * Generates the String representation of the Deadline needed for data storage.
     *
     * @return the Deadline in String format for data storage
     */
    @Override
    public String getStorageString() {
        return "D | " + super.getStorageString() + " | " + this.deadlineDue.format(getDeadlineFormatter());
    }

    /**
     * Generates the String representation of the Deadline.
     *
     * @return the String representation of the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                deadlineDue.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
    }
}