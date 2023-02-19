package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Class constructor.
     * @param description description of the task.
     * @param by date to be completed by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of a deadline.
     *
     * @return the string representation of a deadline.
     */
    @Override
    public String toString () {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("HH:mm, EEEE, MMM dd yyyy")) + ")";
    }
}
