import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String taskName, String deadline) throws DukeException {
        super(taskName);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format, please input as YYYY-MM-DD");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + "(by: " + this.deadline.format(newFormat) + ")";
    }
}
