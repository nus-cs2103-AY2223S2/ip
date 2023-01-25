import java.time.LocalDateTime;

/**
 * The Event class is a type of task.
 *
 * @author Chia Jeremy
 */

public class Event extends Task {

    protected LocalDateTime startDt;
    protected LocalDateTime endDt;

    public Event(String description, LocalDateTime startDt, LocalDateTime endDt) {
        super(description);
        this.startDt = startDt;
        this.endDt = endDt;
    }

    public LocalDateTime getStartDt() {
        return this.startDt;
    }

    public LocalDateTime getEndDt() {
        return this.endDt;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (from: " + this.startDt.toLocalDate() + " " + this.startDt.toLocalTime()
                + " to: " + this.endDt.toLocalDate() + " " + this.endDt.toLocalTime() + ")";
    }
}
