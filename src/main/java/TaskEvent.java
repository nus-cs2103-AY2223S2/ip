import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class representing an Event task.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class TaskEvent extends DukeTask{
    private final LocalDateTime from;
    private final LocalDateTime to;
    public TaskEvent(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)",
                this.from.format(df), this.to.format(df));
    }

    @Override
    public boolean isOnDate(LocalDate dtParsed) {
        return dtParsed.isAfter(this.from.toLocalDate()) && dtParsed.isBefore(this.to.toLocalDate()) ||
                dtParsed.equals(this.from.toLocalDate()) ||
                dtParsed.equals(this.to.toLocalDate());
    }

    @Override
    public String toDBSchema() {
        return String.format(
                "%s|%s|%s|%s",
                "E",
                super.toDBSchema(),
                this.from.format(DateTimeFormatter.ofPattern("d/M/yy HHmm")),
                this.to.format(DateTimeFormatter.ofPattern("d/M/yy HHmm"))
        );
    }
}
