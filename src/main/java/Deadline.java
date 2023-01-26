import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDateTime deadline;

    public Deadline(String name, String deadline) throws DukeException {
        super(name);
        try {
            this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date format in \"dd/mm/yyyy!\"");
        }
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("HHmm, MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by " + this.getDeadline() + ")";
    }
}
