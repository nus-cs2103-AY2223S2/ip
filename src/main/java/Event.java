import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate from;
    protected LocalDate to;
    protected String taskType;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = "E";
    }

    public String toString() {
        String formattedFromDate = this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        String formattedToDate = this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[" + this.taskType + "][" + super.status + "] "
                + super.description
                + " (FROM: " + formattedFromDate
                + " TO: " + formattedToDate
                + ")";
    }
}
