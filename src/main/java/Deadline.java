import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected LocalDateTime by;

    public Deadline(String str, String by) {
        super(str);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String getType() {
        return "D";
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by.format(OUTPUT_FORMAT)
                + ")";
    }
}