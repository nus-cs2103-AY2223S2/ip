import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Events extends Task {
    protected LocalDateTime start;

    protected LocalDateTime end;
    public Events(String description, String start, String end) {

        this(false, description, start, end);
    }

    public Events(boolean isDone, String description, String start, String end) {
        super(isDone, description);
        this.start = LocalDateTime.parse(start.substring(5), FORMATTER);
        this.end = LocalDateTime.parse(end.substring(3), FORMATTER);

    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.start.format(FORMATTER),
                this.end.format(PRINTFORMATTER));
    }

    /**
     * Formats the task into a form where it will be dumped into data.txt.
     */
    public String formatText() {
        String divider = " | ";
        String isMarked = this.isDone ? "1" : "0";
        return "E" + divider + isMarked + divider + this.description + divider +
                this.start + divider + this.end;
    }
}
