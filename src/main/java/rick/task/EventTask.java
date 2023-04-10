package rick.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class EventTask extends RickTask {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs this Task, given a description and the dates between which
     * this task occurs.
     *
     * @param task The task description.
     * @param from The event start dateTime
     * @param to The event end dateTime
     */
    public EventTask(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    /**
     * Generates and returns a human friendly interpretation of this task.
     *
     * @return The task interpretation.
     */
    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)",
                this.from.format(df), this.to.format(df));
    }

    /**
     * Returns a boolean indicating if this task falls on the given date.
     *
     * @param dtParsed The given date.
     * @return The indicative boolean.
     */
    @Override
    public boolean isOnDate(LocalDate dtParsed) {
        return dtParsed.isAfter(this.from.toLocalDate()) && dtParsed.isBefore(this.to.toLocalDate())
                || dtParsed.equals(this.from.toLocalDate())
                || dtParsed.equals(this.to.toLocalDate());
    }

    /**
     * Formats this task into a format for storage in the Storage class, and
     * returns the formatted schema.
     *
     * @return The formatted task.
     */
    @Override
    public String toDbSchema() {
        return String.format(
                "%s|%s|%s|%s",
                "E",
                super.toDbSchema(),
                this.from.format(DateTimeFormatter.ofPattern("d/M/yy HHmm")),
                this.to.format(DateTimeFormatter.ofPattern("d/M/yy HHmm"))
        );
    }
}
