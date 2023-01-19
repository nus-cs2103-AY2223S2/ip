import java.time.LocalDate;

public class Event extends Task {
    private String desc;
    private LocalDate from;
    private LocalDate to;

    public Event(int id, String description, LocalDate from, LocalDate to) {
        super(id);
        desc = description;
        this.from = from;
        this.to = to;
    }

    public String description() {
        return desc;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to %s)", description(), from, to);
    }
}
