import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException{
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Date must be in yyyy-mm-dd format.");
        }
    }

    @Override
    public String toTXT() { return "D | " + super.toTXT() + " | " + by; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
