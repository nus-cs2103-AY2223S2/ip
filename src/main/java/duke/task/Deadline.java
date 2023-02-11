package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    static final String DEADLINE_SYMBOL = "D";

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns String of the Deadline task type, status, description and date.
     * <p>
     * TaskType will be "D" for Deadline.
     * <p>
     * Status will be either "X" or " " depending on mark or unmarked respectively.
     * <p>
     * @return String in the format: [taskType][status] description /by DD-MMM-YYYY
     */
    public String toString() {
        String formattedByDate = this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[" + DEADLINE_SYMBOL + "][" + super.status + "] "
                + super.description
                + " (BY: " + formattedByDate
                + ")";
    }
}
