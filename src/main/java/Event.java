import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Deadline {
    protected LocalDateTime startDate;

    Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description, endDate);
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Event/" + getTaskDescription() + "(from "
                + startDate.format(DateTimeFormatter.ofPattern(DATETIME_DISPLAY_PATTERN)) + " to "
                + endDate.format(DateTimeFormatter.ofPattern(DATETIME_DISPLAY_PATTERN))  + ")";
    }
}
