import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String saveString() {
        return "E;" + this.isDone + ";" + this.description + ";" + this.startDateTime + ";"
                + this.endDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startDateTime.format(Task.format) + "to: "
                + endDateTime.format(Task.format) + ")";
    }
}
