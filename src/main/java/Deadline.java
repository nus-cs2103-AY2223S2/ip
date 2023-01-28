import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    // Attributes:
    protected String by_date;
    protected LocalDate endDate;

    // Constructor:
    public Deadline(String user_input) {
        // use if-else and string.contains to check if this doesn't work
        super(user_input.substring(9, user_input.indexOf("/by ") - 1)); // title
        this.by_date = user_input.substring(user_input.indexOf("/by ") + 4); // due date
        this.endDate = LocalDate.parse(by_date);
    }

    // Methods:
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
