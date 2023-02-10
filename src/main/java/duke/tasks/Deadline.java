package duke.tasks;

import java.time.LocalDate;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected MyDateTime dueDateTime;
    /**
     * Creates a new deadline task.
     * @param description Description of the task.
     * @param dueDateTime Due date and time of the task.
     */
    public Deadline(String description, String dueDateTime) {
        super(description);
        this.dueDateTime = new MyDateTime(dueDateTime.strip());
    }

    @Override
    public String printTask() {
        return String.format("[D]%s (by: %s)", super.printTask(), this.printDateTime());
    }

    @Override
    public String formatForFile() {
        return String.format("%s|%s|%s", "D", super.formatForFile(), this.dueDateTime.formatDateTimeForFile());
    }

    public String printDateTime() {
        return this.dueDateTime.formatDateTimeForPrint();
    }

    /**
     * Checks if this task is due on given date.
     * @param other The given date.
     * @return True if the task is due on given date, else false.
     */
    public boolean isDeadLine(MyDate other) {
        LocalDate current = this.dueDateTime.dateOnly();
        return other.isEqual(current);
    }

    /**
     * Checks if given deadline is a duplicate.
     * @param anotherDeadline Another deadline.
     * @return True if given deadline is a duplicate.
     */
    @Override
    public boolean isEqual(Task anotherDeadline) {
        Deadline other = (Deadline) anotherDeadline;
        boolean isSameDesc = this.description.equals(other.description);
        boolean isSameDateTime = this.dueDateTime.checkSameDateTime(other.dueDateTime);
        return isSameDateTime && isSameDesc;
    }
}
