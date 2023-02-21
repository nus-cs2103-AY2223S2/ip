package duke.tasks;

import java.time.LocalDate;

/**
 * Represents a event task.
 */
public class Event extends Task {

    protected MyDateTime startDateTime;
    protected MyDateTime endDateTime;
    /**
     * Creates a new event task.
     * @param description Description of the task.
     * @param startDateTime Start date and time of the task.
     * @param endDateTime End date and time of the task.
     */
    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = new MyDateTime(startDateTime);
        this.endDateTime = new MyDateTime(endDateTime);
    }

    @Override
    public String printTask() {
        return String.format("[E]%s (from: %s to: %s)", super.printTask(),
                this.printFromDateTime(), this.printToDateTime());
    }

    public String printFromDateTime() {
        return this.startDateTime.formatDateTimeForPrint();
    }

    public String printToDateTime() {
        return this.endDateTime.formatDateTimeForPrint();
    }

    /**
     * Checks if event is occurring on a given date.
     * @param other The given date.
     * @return True if it occurs on given date, else false.
     */
    public boolean liesBetween(MyDate other) {
        LocalDate f = this.startDateTime.dateOnly();
        LocalDate t = this.endDateTime.dateOnly();
        return other.isBetween(f, t);
    }

    @Override
    public String formatForFile() {
        return String.format("%s|%s|%s|%s", "E", super.formatForFile(),
                this.startDateTime.formatDateTimeForFile(), this.endDateTime.formatDateTimeForFile());
    }

    /**
     * Checks if given event is a duplicate.
     * @param anotherEvent Another event.
     * @return True if given event is a duplicate.
     */
    @Override
    public boolean isEqual(Task anotherEvent) {
        Event other = (Event) anotherEvent;
        boolean isSameDesc = super.isEqual(other);
        boolean isSameStartDateTime = this.startDateTime.checkSameDateTime(other.startDateTime);
        boolean isSameEndDateTime = this.endDateTime.checkSameDateTime(other.endDateTime);
        return isSameEndDateTime && isSameStartDateTime && isSameDesc;
    }
}
