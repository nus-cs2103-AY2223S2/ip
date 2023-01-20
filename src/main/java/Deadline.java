import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String name, String by) throws DateTimeParseException {
        super(name);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: "
            + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
 }
