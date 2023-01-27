package duke.tasks;

import java.time.LocalDate;

public class Deadline extends Task {
    protected MyDateTime by;

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

    public boolean isDeadLine(MyDate other) {
        LocalDate current = this.by.dateOnly();
        return other.isEqual(current);
    }
}
