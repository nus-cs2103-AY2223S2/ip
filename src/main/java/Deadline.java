import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        LocalDate date = LocalDate.parse(by);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = date.format(format);
        this.by = formattedDate;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    @Override
    public String getDetailedDescription() {
        return super.description + " | " + this.by;
    }

    @Override
    public String getSymbol() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
