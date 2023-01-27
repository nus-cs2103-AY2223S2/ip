package duke.tasks;

import java.time.LocalDate;

public class Event extends Task {

    protected MyDateTime startDateTime;
    protected MyDateTime endDateTime;
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
}
