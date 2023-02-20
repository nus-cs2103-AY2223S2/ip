package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The {@code DeadlineTask} class for tasks with a deadline.
 */
public class DeadlineTask extends Task {

    private final LocalDate by;

    /**
     * Instantiates a new {@code DeadlineTask} object.
     *
     * @param name The name of the task.
     * @param by   The due date of the task.
     */
    public DeadlineTask(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Copy constructor for the {@code DeadlineTask} class.
     *
     * @param other The {@code DeadlineTask} to be deep copied.
     */
    public DeadlineTask(DeadlineTask other) {
        super(other);
        by = other.by;
    }

    public DeadlineTask deepClone() {
        return new DeadlineTask(this);
    }

    /**
     * Checks whether this {@code DeadlineTask} is due on {@code date}
     *
     * @param date A due date.
     * @return A boolean indicating if this deadline task is due on {@code date}.
     */
    public boolean isDueOn(LocalDate date) {
        return by.equals(date);
    }

    public String toDukeFileString() {
        return "D|" + super.toDukeFileString() + "|" + by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedByString = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedByString + ")";
    }
}
