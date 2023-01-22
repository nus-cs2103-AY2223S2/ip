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
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.by + "\n";
    }
}

