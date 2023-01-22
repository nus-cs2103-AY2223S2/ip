package book.task;

import java.time.LocalDateTime;

/**
 * Subclass of {@code Task} representing an {@code Deadline}.
 */
public class Deadline extends Task {
    /** {@code LocalDateTime} representing the deadline of the {@code Deadline}. */

    private LocalDateTime dateTime;

    /**
     * Initializes a {@code Deadline} object.
     * @param description {@code String} description of the {@code Task}.
     * @param dateTime {@code LocalDateTime} representing the deadline of the {@code Deadline}.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns the {@code String} representation of a {@code Deadline} for saving.
     * @return {@code String} representation of a {@code Deadline} for saving.
     */
    @Override
    public String saveString() {
        return "D;" + this.isDone + ";" + this.description + ";"
                + dateTime.format(Task.saveFormat);
    }

    /**
     * Returns the {@code String} representation of the {@code Deadline}.
     * @return {@code String} representation of the {@code Deadline}.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(Task.printFormat) + ")";
    }
}
