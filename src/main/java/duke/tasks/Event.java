package duke.tasks;

import java.time.LocalDate;

/**
 * Represents a event task.
 */
public class Event extends Task {

    protected MyDateTime from;
    protected MyDateTime to;

    /**
     * Creates a new event task.
     * @param description Description of the task.
     * @param from Start date and time of the task.
     * @param to End date and time of the task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = new MyDateTime(from);
        this.to = new MyDateTime(to);
    }

    @Override
    public String printTask() {
        return String.format("[E]%s (from: %s to: %s)", super.printTask(),
                this.printFromDateTime(), this.printToDateTime());
    }

    public String printFromDateTime() {
        return this.from.formatDateTimeForPrint();
    }

    public String printToDateTime() {
        return this.to.formatDateTimeForPrint();
    }

    /**
     * Checks if event is occurring on a given date.
     * @param other The given date.
     * @return True if it occurs on given date, else false.
     */
    public boolean liesBetween(MyDate other) {
        LocalDate f = this.from.dateOnly();
        LocalDate t = this.to.dateOnly();
        return other.isBetween(f, t);
    }

    @Override
    public String formatForFile() {
        return String.format("%s|%s|%s|%s", "E", super.formatForFile(),
                this.from.formatDateTimeForFile(), this.to.formatDateTimeForFile());
    }
}
