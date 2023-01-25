import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task{
    private LocalDate from;
    private LocalDate to;
    private String name;
    private boolean isDone;

    public Event(String name, String from, String to) {
        super(name, false);
        this.name = name;
        this.isDone = false;
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public Event(String name, String from, String to, boolean isDone) {
        super(name, isDone);
        this.name = name;
        this.isDone = isDone;
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public String getStatus() {
        return isDone ? "1" : "0";
    }

    @Override
    public String getDescription() {
        return name + "," + from + "," + to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
