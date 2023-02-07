import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = transferTOLocalDateTime(by);
    }

    public LocalDateTime transferTOLocalDateTime(String by) {
        LocalDateTime dateTime = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            dateTime = LocalDateTime.parse(by, dateTimeFormatter);
            return dateTime;
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("\t Wrong Wrong Date Format!\n");
        }
        return dateTime;
    }

    public String deadlineDateTime() {
        String deadline = "";
        String dayOfWeek = "";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        try {
            deadline = this.by.format(format);
            dayOfWeek = this.by.getDayOfWeek().toString();
            return dayOfWeek + ", " + deadline;
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("\t Wrong Wrong Date Format!\n");
        }
        return dayOfWeek + ", " + deadline;
    }

    public String getBy() {
        String deadline = deadlineDateTime();
        return deadline;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDateTime() + ")";
    }
}
