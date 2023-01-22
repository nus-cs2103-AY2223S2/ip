import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byDate;
    protected String byTime;
    public Deadline(String description, String byDate, String byTime) {
        super(description);
        this.byDate = LocalDate.parse(byDate);
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + "(by: " + this.byDate.format(formatter) + " " + this.byTime + ")";
    }
}
