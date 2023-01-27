package duke.tasks;

import java.time.LocalDate;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected MyDateTime by;

    /**
     * Creates a new deadline task.
     * @param description Description of the task.
     * @param by Due date and time of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = new MyDateTime(by);
    }

    @Override
    public String printTask() {
        return String.format("[D]%s (by: %s)", super.printTask(), this.printDateTime());
    }

    @Override
    public String formatForFile() {
        return String.format("%s|%s|%s", "D", super.formatForFile(), this.by.formatDateTimeForFile());
    }

    public String printDateTime() {
        return this.by.formatDateTimeForPrint();
    }

    /**
     * Checks if this task is due on given date.
     * @param other The given date.
     * @return True if the task is due on given date, else false.
     */
    public boolean isDeadLine(MyDate other) {
        LocalDate current = this.by.dateOnly();
        return other.isEqual(current);
    }
}
