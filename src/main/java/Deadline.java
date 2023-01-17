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

    public Deadline(String title, String deadline, boolean done) {
        super(title);
        this.deadline = deadline;
        this.type = "[D]";
        this.done = done;
        try {
            this.dateline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    @Override
    public String toData() {
        return this.type + " | " + (this.done ? "1" : "0") + " | " + this.title + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dateline.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy")) + ")";
    }
}
