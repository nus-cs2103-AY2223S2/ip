package duke.tasks;

import java.time.LocalDate;

public class Deadline extends Task {
    protected MyDateTime dueDateTime;

    public Deadline(String description, String dueDateTime) {
        super(description);
        this.dueDateTime = new MyDateTime(dueDateTime);
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

    public boolean isDeadLine(MyDate other) {
        LocalDate current = this.dueDateTime.dateOnly();
        return other.isEqual(current);
    }
}
