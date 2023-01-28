package AddTasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadlines extends Task {

    protected LocalDate by;

    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String convert = null;
        try {
            convert = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (NullPointerException e) {
        }
        return " " + "[D]" + "[" + super.getStatusIcon() + "]" + super.toString() + "(By: " + convert + ")";
    }
}