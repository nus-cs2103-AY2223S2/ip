package alfred.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Child class of task that represents a task with a specific date as deadline.
 */
public class Deadline extends Task {

    protected LocalDate date;

    /**
     * Creates an instance of deadline.
     * @param description
     * @param date
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getStorageDetails() {
        return this.description + " | " + this.date.toString();
    }

    @Override
    public String getEventType() {
        return "D";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return "[D]" + super.toString() + " (by: " + this.date.format(formatter) + ")";
    }
}
