import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected String by;
    LocalDateTime dateTime;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTime = LocalDateTime.parse(by.trim(), formatter);
    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
    }
}

