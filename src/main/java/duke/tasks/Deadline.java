package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents user deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime endDate;

    /**
     * Constructor of Deadline class.
     *
     * @param description description of the Deadline task.
     * @param by end datetime of the Deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.endDate = by;
    }

    /**
     * Returns string representation of the end datetime.
     *
     * @return string representation of the end datetime
     */
    public String getByDate() {
        return this.endDate.toString();
    }

    /**
     *  Returns string representation of the object.
     *
     * @return a string representation of the object
    */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String dateTimeString = endDate.format(format);
        return "[D]" + super.toString() + " (by: " + dateTimeString + ")";
    }
}
