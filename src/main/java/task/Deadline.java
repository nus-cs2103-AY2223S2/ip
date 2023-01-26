package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Creates a Deadline
     * @param text the given text description
     * @param date the end deadline
     */
    public Deadline(String text, LocalDate date) {
        super(text);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy GGGG")) + ")";
    }
}
