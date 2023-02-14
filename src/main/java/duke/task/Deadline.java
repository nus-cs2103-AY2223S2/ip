package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline that is a Task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("EEEE, "
            + "MMMM d yyyy, h:mm a");
    private String doBy;
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline
     * @param description Description of Deadline.
     * @param deadline Date of deadline.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.doBy = deadline;
        this.deadline = LocalDateTime.parse(deadline);
    }

    /**
     * Translates the task into data format.
     * @return A String that represents the task in data format.
     */
    public String toData() {
        return String.format("D | %s | %s | %s", this.getStatusIcon(), this.getDescription(), this.doBy);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(),
                this.getDescription(), this.deadline.format(DATE_TIME_FORMATTER));
    }
}
