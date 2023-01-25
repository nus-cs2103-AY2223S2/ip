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

    public String printDateTime() {
        return this.by.printDateTime();
    }

    public boolean isDeadLine(MyDate other) {
        LocalDate current = this.by.dateOnly();
        return other.isEqual(current);
    }
}