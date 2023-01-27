import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The Event class is a type of task.
 *
 * @author Chia Jeremy
 */

public class Event extends Task {

    protected LocalDateTime startDT;
    protected LocalDateTime endDT;
    private final LocalDate startDate;
    private final LocalTime startTime;
    private final LocalDate endDate;
    private final LocalTime endTime;

    public Event(String description, LocalDateTime startDT, LocalDateTime endDT) {
        super(description);
        this.startDT = startDT;
        this.endDT = endDT;
        this.startDate = startDT.toLocalDate();
        this.startTime = startDT.toLocalTime();
        this.endDate = endDT.toLocalDate();
        this.endTime = endDT.toLocalTime();
    }

    public LocalDateTime getStartDT() {
        return this.startDT;
    }

    public LocalDateTime getEndDT() {
        return this.endDT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.startDate + " " + this.startTime
                + " to: " + this.endDate + " " + this.endTime + ")";
    }
}
