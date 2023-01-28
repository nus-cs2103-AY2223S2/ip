import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

public class Event extends Task{
    private final String taskType = "[E]";
    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);

        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
    }

    @Override
    public String toString() {
        return taskType + super.toString() + " (from: "
                + DateTimeFormatter.ofLocalizedDate((FormatStyle.FULL)).format(this.startTime) + " to: "
                + DateTimeFormatter.ofLocalizedDate((FormatStyle.FULL)).format(this.endTime) + ")";
    }
}
