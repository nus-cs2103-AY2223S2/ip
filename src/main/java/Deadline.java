import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by;
    protected LocalDateTime byDateTime;

    public Deadline(String TaskName, String by) {
        super(TaskName);
        this.by = by;
        try {
            this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        }
        catch (DateTimeParseException err) {
            this.byDateTime = null;
        }
    }

    public String getByTime() {
        if (this.byDateTime != null) {
            return this.byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm")) + " hrs";
        }
        else {
            return this.by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatus() + "] " + super.toString().strip()
                + " (by: " + this.getByTime() + ")";
    }

    @Override
    public String toSaveString() {
        return "D" + "=" + super.getStatus() + "=" + super.toSaveString().strip()
                + "=" + this.by;
    }
}
