import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    String deadline;
    LocalDateTime dateline;
    public Deadline(String title, String deadline) throws DateTimeParseException {
        super(title);
        this.deadline = deadline;
        this.type = "[D]";
        try {
            this.dateline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dateline.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy")) + ")";
    }
}
