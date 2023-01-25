import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        String byStr = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        return "[D]" + super.toString() + " (by: " + byStr + ")";
    }
}