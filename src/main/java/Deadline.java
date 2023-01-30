import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) throws DukeException{
        super(description);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        String dateFormat = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[D]" + super.toString() + "(by: " + dateFormat + ")";
    }
    @Override
    public String toFileString() {
        String dateFormat = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, dateFormat);
    }
}