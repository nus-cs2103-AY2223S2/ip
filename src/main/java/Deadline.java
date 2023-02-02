import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{

    protected String by;
    protected LocalDate byDate;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for date!");
        }
    }

    public Deadline(String description, String by, String status) throws DukeException {
        super(description, status);
        this.by = by;
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for date!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + this.by;
    }
}
