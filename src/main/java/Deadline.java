import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected String date;
    protected LocalTime time = LocalTime.of(23,59);

    public Deadline(String description, String date) {
        super(description);
        LocalDate d = LocalDate.parse(date);
        this.date = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Deadline(String description, String date, String time) {
        this(description, date);
        this.time = LocalTime.parse(time);
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + " " + time + ")";
    }
}