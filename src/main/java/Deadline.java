import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        String[] dateAndTime = by.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (dateAndTime.length == 1) by += " 2359";
        this.by = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + formatter.format(this.by) + ")";
    }
}