import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = transferTOLocalDateTime(by);
    }

    public LocalDateTime transferTOLocalDateTime(String by) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(by, dateTimeFormatter);
        return dateTime;
    }

    public String deadlineDateTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String deadline = this.by.format(format);
        String dayOfWeek = this.by.getDayOfWeek().toString();
        return dayOfWeek + ", " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDateTime() + ")";
    }
}
