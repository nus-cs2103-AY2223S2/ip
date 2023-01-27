import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDate by;
    public Deadline(String taskName, String by) throws DukeException {
        super(taskName);
        try {
            LocalDate byDate = LocalDate.parse(by);
            this.by = byDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("Format of date was not recognized");
        }
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
