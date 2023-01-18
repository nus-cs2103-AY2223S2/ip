import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String dataFormat1;
    private String dataFormat2;

    public Event(String taskName, LocalDateTime startTime, LocalDateTime endTime, String dataFormat1, String dataFormat2) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
        this.dataFormat1 = dataFormat1;
        this.dataFormat2 = dataFormat2;
    }

    public Event(String taskName, Boolean isDone, String startData, String endData) {
        super(taskName, isDone);
        this.startTime = LocalDateTime.parse(startData);
        this.endTime = LocalDateTime.parse(endData);
        this.dataFormat1 = startData;
        this.dataFormat2 = endData;
    }

    @Override
    public String dataFormat() {
        return "E|" + super.dataFormat() + "|" + this.dataFormat1 + "|" + this.dataFormat2;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + this.formatDateTime(this.startTime)
                + " to: "
                + this.formatDateTime(this.endTime)
                + ")";
    }
}
