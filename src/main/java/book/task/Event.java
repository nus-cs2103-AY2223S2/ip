package book.task;

import java.time.LocalDateTime;

/**
 * Subclass of {@code Task} representing an {@code Event}.
 */
public class Event extends Task {
    /** {@code LocalDateTime} representing the start of the {@code Event}. */
    private LocalDateTime startDateTime;
    /** {@code LocalDateTime} representing the end of the {@code Event}. */
    private LocalDateTime endDateTime;

    /**
     * Initializes an {@code Event} object.
     * @param description {@code String} description of the {@code Task} object.
     * @param startDateTime {@code LocalDateTime} representing the start of the {@code Event}.
     * @param endDateTime {@code LocalDateTime} representing the end of the {@code Event}.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the {@code String} representation of a {@code Event} for saving.
     * @return {@code String} representation of a {@code Event} for saving.
     */
    @Override
    public String saveString() {
        return "E;" + this.isDone + ";" + this.description + ";"
                + startDateTime.format(Task.saveFormat) + ";"
                + endDateTime.format(Task.saveFormat);
    }

    /**
     * Returns the {@code String} representation of the {@code Event}.
     * @return {@code String} representation of the {@code Event}.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDateTime.format(Task.printFormat)
                + " to: " + endDateTime.format(Task.printFormat) + ")";
    }
}
