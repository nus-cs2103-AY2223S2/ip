import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        String byFormatted = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }
}