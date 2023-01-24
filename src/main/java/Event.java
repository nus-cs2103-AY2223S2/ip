import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = LocalDateTime.parse(startDate, inputDateTimeFormatter);
        this.endDate = LocalDateTime.parse(endDate, inputDateTimeFormatter);
    }

    @Override
    public String toString() {
        return "[" + TaskType.E + "]" + super.toString() + " (from: "
                + this.startDate.format(this.outputDateTimeFormatter) + " to: "
                + this.endDate.format(this.outputDateTimeFormatter) + ")";
    }
}
