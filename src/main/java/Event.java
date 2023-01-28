import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    // Attributes:
    protected String from_date;
    protected LocalDate startDate;
    protected String to_date;
    protected LocalDate endDate;

    // Constructor:
    public Event(String user_input) {
        super(user_input.substring(6, user_input.indexOf("/from ") - 1));
        this.from_date = user_input.substring(user_input.indexOf("/from ") + 6, user_input.indexOf("/to ") - 1);
        this.startDate = LocalDate.parse(from_date);
        this.to_date = user_input.substring(user_input.indexOf("/to ") + 4);
        this.endDate = LocalDate.parse(to_date);
    }

    // Methods:
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
